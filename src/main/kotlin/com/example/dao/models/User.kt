package com.example.dao.models

data class User(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val salt: String
)