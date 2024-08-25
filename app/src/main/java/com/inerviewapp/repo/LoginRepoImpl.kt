package com.inerviewapp.repo

import com.inerviewapp.api.AppApi
import com.inerviewapp.utils.network.ApiResponse
import com.inerviewapp.utils.ErrorLogger
import javax.inject.Inject

class LoginRepoImpl @Inject constructor(
    private val api: AppApi,
    private val error: ErrorLogger,

    ) : LoginRepo {
    override suspend fun login(email: String, password: String): ApiResponse {

        return try {
            val map: HashMap<String, Any> = HashMap()
            map["email"] = email
            map["password"] = password
            map["device_id"] = "asasa"
            map["device_type"] = "android"

            val result = api.login(map)

            error.logError("LoginApi", result.body().toString())

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