package com.inerviewapp.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.inerviewapp.model.ImageUI

class ImageController(private val onImageClick: (Int) -> Unit) : TypedEpoxyController<ImageUI>() {
    override fun buildModels(imageData: ImageUI?) {

        imageData?.images?.forEach {
            ImageEpoxyModel(it) { selectedImage ->
                repeat(imageData.images.size - 1) { position ->
                    val image = imageData.images[position]
                    if (selectedImage == image) onImageClick.invoke(position)
                    else onImageClick.invoke(-1)
                }
            }.id(it).addTo(this)
        }
    }

}

