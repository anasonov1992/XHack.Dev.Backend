package com.example.data.models

@kotlinx.serialization.Serializable
data class AddUserToTeamDto(
    val userId: Int,
    val teamId: Int
)
