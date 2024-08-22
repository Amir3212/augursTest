package com.inerviewapp.repo

import com.inerviewapp.utils.network.ApiResponse

interface LoginRepo {
    suspend fun login(email: String, password: String): ApiResponse
}