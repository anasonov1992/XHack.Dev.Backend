package com.example.data.models

@kotlinx.serialization.Serializable
data class TeamDto(
    val id: Int,
    val name: String,
    val description: String,
    val avatarUrl: String?,
    val maxUsersCount: Int,
    val users: List<UserDto>
 )
