package com.example.dao.mappers

import com.example.dao.entities.User
import com.example.data.models.AuthUserDto
import com.example.data.models.UserDto

fun User.toAuthUserDto(): AuthUserDto = AuthUserDto(id.value, username, email, password, salt)
fun User.toUserDto(): UserDto = UserDto(id.value, username, email, password)