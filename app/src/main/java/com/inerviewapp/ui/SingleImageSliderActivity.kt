package com.inerviewapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.inerviewapp.adapter.ImageSliderAdapter
import com.inerviewapp.databinding.ActivitySingleImageSliderBinding
import com.inerviewapp.utils.redux.ApplicationState
import com.inerviewapp.utils.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleImageSliderActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleImageSliderBinding

    private var position: Int = 0

    @Inject
    lateinit var store: Store<ApplicationState>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleImageSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        position = intent.getIntExtra("position", 0)


        setDataWithPosition()
    }

    private fun setDataWithPosition() {

        Log.d("TAG", "setDataWithPosition: $position")
        val images = store.stateFlow.value.images


        val adapter = ImageSliderAdapter(images)

        binding.apply {
            viewPager2.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager2) { _, _ -> }.attach()

            viewPager2.currentItem = position
        }


    }
}