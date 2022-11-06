package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.User
import com.example.dao.interfaces.UsersDataSource
import com.example.dao.mappers.toAuthUserDto
import com.example.dao.mappers.toUserDto
import com.example.dao.tables.Users
import com.example.data.models.AuthUserDto
import com.example.data.models.RegisterUserDto
import com.example.data.models.UserDto

class UsersDataSourceImpl : UsersDataSource {
    override suspend fun getUsers(): List<UserDto> = dbQuery {
        User.all().map { it.toUserDto() }.toList()
    }

    override suspend fun getByEmail(email: String): AuthUserDto? = dbQuery {
        User.find { Users.email eq email }.map { it.toAuthUserDto() }.firstOrNull()
    }

    override suspend fun registerUser(user: RegisterUserDto): UserDto = dbQuery {
        User.new {
            username = user.username
            email = user.email
            password = user.password
            salt = user.salt
        }.toUserDto()
    }
}