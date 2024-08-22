package com.inerviewapp.api

import com.inerviewapp.dto.loginResponseDto.LoginresponseDTO
import com.inerviewapp.dto.productResponseDto.ProductResponseDTO
import com.inerviewapp.utils.network.interceptor.Authentication
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface AppApi {

    @POST("api/user/login")
    suspend fun login(
        @QueryMap map: HashMap<String, Any>,
    ): Response<LoginresponseDTO>


    @FormUrlEncoded
    @Authentication
    @POST("api/user/product-toolkit/details")
    suspend fun detail(
        @Field("id") id: String = "9",
        @Field("filter_name") name: String = "",
    ): Response<ProductResponseDTO>
}