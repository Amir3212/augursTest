package com.inerviewapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inerviewapp.dto.loginResponseDto.LoginresponseDTO
import com.inerviewapp.repo.LoginRepo
import com.inerviewapp.utils.db.sharedPrefs.AppPrefs
import com.inerviewapp.utils.network.ApiEvents
import com.inerviewapp.utils.network.ApiResponse
import com.inerviewapp.utils.useCases.validateEmail
import com.inerviewapp.utils.useCases.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appPrefs: AppPrefs,
    private val loginRepo: LoginRepo,
) : ViewModel() {


    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState

    private val _loginChannel = Channel<ApiEvents>()
    val loginChannel = _loginChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailEvent -> _loginState.update {
                it.copy(
                    email = event.email
                )
            }

            LoginEvent.Login -> validateFields()

            is LoginEvent.PasswordEvent -> _loginState.update {
                it.copy(password = event.password)
            }
        }
    }

    private fun validateFields() {
        val email = _loginState.value.email.validateEmail()
        val password = _loginState.value.password.validatePassword()

        val hasError = listOf(
            email, password
        ).any { !it.success }


        _loginState.update {
            it.copy(
                emailError = email.error,
                passwordError = password.error,
            )
        }
        if (!hasError) {
            login()
        }

    }

    private fun login() {
        viewModelScope.launch {
            _loginChannel.send(
                ApiEvents.Loading(true)
            )
            loginState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = loginRepo.login(loginState.value.email, loginState.value.password)) {
                is ApiResponse.Success<*> -> {

                    val data = result.responseData as LoginresponseDTO
                    appPrefs.token = data.token

                    _loginChannel.send(
                        ApiEvents.Success(data.message)
                    )

                    loginState.update {
                        it.copy(
                            isLoggedInSuccessfully = data.status,
                            isLoading = false
                        )
                    }
                }

                is ApiResponse.Failed<*> -> {
                    loginState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    _loginChannel.send(
                        ApiEvents.Success(result.errorMsg)
                    )
                }


            }


        }
    }
}


sealed class LoginEvent {
    data class EmailEvent(val email: String) : LoginEvent()
    data class PasswordEvent(val password: String) : LoginEvent()
    data object Login : LoginEvent()
}

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val message: String? = null,
    val isLoggedInSuccessfully: Boolean = false,
)
