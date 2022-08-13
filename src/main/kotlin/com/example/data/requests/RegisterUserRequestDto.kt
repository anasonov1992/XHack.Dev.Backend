package com.example.data.requests

@kotlinx.serialization.Serializable
data class RegisterUserRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
