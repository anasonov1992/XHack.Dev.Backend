package com.example.data.models

import com.example.primitives.RequestType

@kotlinx.serialization.Serializable
data class RequestDto (
    val id: Int,
    val user: UserDto? = null,
    val team: TeamDto? = null,
    val type: RequestType,
    val isCanceled: Boolean
)