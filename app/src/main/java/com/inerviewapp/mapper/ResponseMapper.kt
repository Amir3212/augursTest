package com.inerviewapp.mapper

import com.inerviewapp.dto.productResponseDto.ProductResponseDTO

fun ProductResponseDTO.toImages(): List<String> {
    return this.data.image.map {
        it.image
    }
}