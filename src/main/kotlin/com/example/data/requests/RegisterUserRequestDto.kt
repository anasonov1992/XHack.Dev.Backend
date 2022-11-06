package com.example.data.requests

@kotlinx.serialization.Serializable
data class RegisterUserRequestDto(
    val username: String,
    val email: String,
    val password: String
)
