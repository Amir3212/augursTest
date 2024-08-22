package com.inerviewapp.utils.network.responseHandler

import com.utils.errorLogger.ErrorLogger
import com.inerviewapp.utils.network.ApiResponse
import com.utils.network.responseHandler.getResponseAfterValidation
import retrofit2.Response
import javax.inject.Inject

class ResponseHandlerImpl @Inject constructor(
    private val logger: ErrorLogger,
) : ResponseHandler {
    override fun handleResponse(response: Response<*>): ApiResponse {
        logger.logError(
            error = response.body(),
            tag = response.raw().request.url.toString()
        )
        return response.getResponseAfterValidation()
    }
}