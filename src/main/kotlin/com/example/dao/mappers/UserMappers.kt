package com.example.dao.mappers

import com.example.dao.entities.User
import com.example.data.models.UserDto

fun User.toUserDto(): UserDto = UserDto(id.value, firstName, lastName, email, password, salt)