package com.inerviewapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inerviewapp.dto.productResponseDto.ProductResponseDTO
import com.inerviewapp.mapper.toImages
import com.inerviewapp.model.ImageUI
import com.inerviewapp.repo.images.ImagesRepo
import com.inerviewapp.utils.network.ApiResponse
import com.inerviewapp.utils.redux.ApplicationState
import com.inerviewapp.utils.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repo: ImagesRepo,
    private val store: Store<ApplicationState>,
) : ViewModel() {


    private val _event = Channel<ProductEvent>()
    val event = _event.receiveAsFlow()

    fun fetchImages() {
        viewModelScope.launch {
            when (val result = repo.loadImages()) {
                is ApiResponse.Success<*> -> {
                    val data = result.responseData as ProductResponseDTO
                    if (!data.status) {
                        _event.send(ProductEvent.Error(data.message))
                        return@launch
                    }


                    store.update { return@update it.copy(images = data.toImages()) }
                }

                is ApiResponse.Failed<*> -> {
                    _event.send(ProductEvent.Error(result.errorMsg))
                }
            }
        }
    }

    val images = combine(
        store.stateFlow.map { it.images },
        store.stateFlow.map { it.token },
    ) { images, _ ->
        ImageUI(
            images = images,
        )
    }
}

sealed class ProductEvent {
    data class Error(val msg: String) : ProductEvent()
}