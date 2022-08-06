package com.example.dao.interfaces

import com.example.data.models.UserDto

interface UsersDataSource {
    suspend fun getUsers(): List<UserDto>
    suspend fun getByEmail(email: String): UserDto?
    suspend fun createUser(user: UserDto): UserDto
}