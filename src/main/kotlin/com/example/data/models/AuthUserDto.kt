package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthUserDto(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val salt: String
)
