package com.inerviewapp.utils.redux

data class ApplicationState(
    val token: String = "",
    val images: List<String> = emptyList(),
)
