package com.inerviewapp.repo.images

import com.inerviewapp.utils.network.ApiResponse

interface ImagesRepo {
    suspend fun loadImages(): ApiResponse
}