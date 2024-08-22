package com.inerviewapp.dto.loginResponseDto

data class CreatedBy(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val role: String,
    val role_slug: String,
    val user_name: String
)