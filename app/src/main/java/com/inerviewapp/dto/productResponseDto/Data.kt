package com.inerviewapp.dto.productResponseDto

data class Data(
    val created_at: String,
    val created_by: Int,
    val icon_image: String,
    val id: Int,
    val image: List<Image>,
    val image_brochuers: List<ImageBrochuer>,
    val image_drawing: List<ImageDrawing>,
    val image_installation: List<ImageInstallation>,
    val image_specification: List<ImageSpecification>,
    val main_image: String,
    val product_code: String,
    val product_name: String
)