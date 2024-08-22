package com.inerviewapp.repo.images

import com.inerviewapp.api.AppApi
import com.inerviewapp.utils.db.sharedPrefs.AppPrefs
import com.inerviewapp.utils.network.ApiResponse
import com.utils.errorLogger.ErrorLogger
import javax.inject.Inject

class ImagesRepoImpl @Inject constructor(
    private val api: AppApi,
    private val error: ErrorLogger,
    private val appPrefs: AppPrefs,
) : ImagesRepo {
    override suspend fun loadImages(): ApiResponse {
        return try {


            if (appPrefs.token == null) ApiResponse.Failed(
                responseData = null, errorMsg = "Failed to fetch token not found !!"
            )

            val result = api.detail()

            error.logError("fetch Images Api", result.body().toString())
            if (result.isSuccessful && result.code() == 200) {
                ApiResponse.Success(responseData = result.body())
            } else ApiResponse.Failed(
                responseData = null, errorMsg = "failed to login\n response code ${result.code()}"
            )


        } catch (ex: Exception) {
            ex.localizedMessage?.let { error.logError("LoginApi Error", it) }
            ApiResponse.Failed(
                responseData = null,
                errorMsg = "failed to login\n response code ${ex.localizedMessage}"
            )
        }
    }
}