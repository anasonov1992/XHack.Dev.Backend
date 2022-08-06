package com.example.data.models

@kotlinx.serialization.Serializable
data class UserDto(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val salt: String
)