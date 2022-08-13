package com.example.data.requests

@kotlinx.serialization.Serializable
data class AuthRequestDto(
    val email: String,
    val password: String
)