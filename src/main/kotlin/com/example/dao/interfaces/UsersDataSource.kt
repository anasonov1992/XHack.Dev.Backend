package com.example.dao.interfaces

import com.example.dao.entities.User
import com.example.data.models.UserDto

interface UsersDataSource {
    suspend fun getByEmail(email: String): User?
    suspend fun createUser(user: UserDto): User
}