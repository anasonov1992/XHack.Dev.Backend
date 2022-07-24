package com.example.dao.interfaces

import com.example.dao.models.User

interface UserDataSource {
    suspend fun getByUsername(username: String): User?
    suspend fun createUser(user: User): User?
}