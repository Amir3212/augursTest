package com.inerviewapp.dto.loginResponseDto

data class LoginresponseDTO(
    val message: String,
    val profile: Profile,
    val status: Boolean,
    val token: String
)