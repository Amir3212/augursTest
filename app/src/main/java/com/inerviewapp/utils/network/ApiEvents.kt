package com.inerviewapp.utils.network

sealed class ApiEvents {
    data class Success<ResponseType>(val data: ResponseType) : ApiEvents()
    data class Failure(val error: Any) : ApiEvents()
    data class Loading(val loading: Boolean) : ApiEvents()
}
