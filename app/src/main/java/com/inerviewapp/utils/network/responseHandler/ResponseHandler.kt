package com.inerviewapp.utils.network.responseHandler


import com.inerviewapp.utils.network.ApiResponse
import retrofit2.Response

interface ResponseHandler {
    fun handleResponse(response: Response<*>): ApiResponse
}