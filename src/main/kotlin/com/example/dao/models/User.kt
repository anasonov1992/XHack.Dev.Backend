package com.example.dao.models

data class User(
    val id: Int = 0,
    val username: String,
    val password: String,
    val salt: String
)