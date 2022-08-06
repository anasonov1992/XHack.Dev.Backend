package com.example.data.models

@kotlinx.serialization.Serializable
data class InviteUserDto(
    val userId: Int?,
    val teamId: Int?
)
