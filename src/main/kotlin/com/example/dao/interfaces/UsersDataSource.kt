package com.example.dao.interfaces

import com.example.data.models.AuthUserDto
import com.example.data.models.RegisterUserDto
import com.example.data.models.UserDto

interface UsersDataSource {
    suspend fun getUsers(): List<UserDto>
    suspend fun getByEmail(email: String): AuthUserDto?
    suspend fun registerUser(user: RegisterUserDto): UserDto
}