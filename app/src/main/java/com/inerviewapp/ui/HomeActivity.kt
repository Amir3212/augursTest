package com.inerviewapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.inerviewapp.controller.ImageController
import com.inerviewapp.databinding.ActivityHomeBinding
import com.inerviewapp.utils.extension.showToast
import com.inerviewapp.utils.flowCollector.collectFlow
import com.inerviewapp.viewModels.ImagesViewModel
import com.inerviewapp.viewModels.ProductEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: ImagesViewModel
    private lateinit var controller: ImageController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ImagesViewModel::class.java]
        setContentView(binding.root)

        controller = ImageController {
            if (it > 0) startActivity(
                Intent(this, SingleImageSliderActivity::class.java).putExtra("position", it)
            )
        }


        binding.imageController.setController(controller)
        viewModel.fetchImages()


        collectFlow(viewModel.images) {
            controller.setData(it)
            binding.progressBar2.isVisible = it.images.isEmpty()
        }

        collectFlow(viewModel.event) {

            when (it) {
                is ProductEvent.Error -> {
                    showToast(it.msg)
                    binding.progressBar2.isVisible = false
                }
            }
        }

    }
}