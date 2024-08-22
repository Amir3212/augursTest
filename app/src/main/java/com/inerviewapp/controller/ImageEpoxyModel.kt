package com.inerviewapp.controller

import com.inerviewapp.R
import com.inerviewapp.databinding.CustomImageViewBinding
import com.inerviewapp.utils.epoxy.ViewBindingKotlinModel


data class ImageEpoxyModel(
    val imageData: String,
    val onImageClick: (String) -> Unit,

    ) : ViewBindingKotlinModel<CustomImageViewBinding>(R.layout.custom_image_view) {
    override fun CustomImageViewBinding.bind() {
        image = imageData
        mainImageCardView.setOnClickListener {
            onImageClick.invoke(imageData)
        }


    }
}