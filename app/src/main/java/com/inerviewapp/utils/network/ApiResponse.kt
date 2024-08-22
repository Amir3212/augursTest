package com.inerviewapp.utils.network


open class ApiResponse {
    data class Success<ResponseType>(val responseData: ResponseType) : ApiResponse()
    data class Failed<ResponseType>(val responseData: ResponseType? = null, val errorMsg: String) :
        ApiResponse()
}
