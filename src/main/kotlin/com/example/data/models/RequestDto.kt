package com.example.data.models

import com.example.primitives.RequestType

data class RequestDto(
    val id: Int,
    val user: UserDto,
    val team: TeamDto,
    val type: RequestType,
    val isCanceled: Boolean
)
