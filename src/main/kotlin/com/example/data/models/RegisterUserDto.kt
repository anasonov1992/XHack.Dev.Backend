package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserDto(
    val username: String,
    val email: String,
    val password: String,
    val salt: String
)
