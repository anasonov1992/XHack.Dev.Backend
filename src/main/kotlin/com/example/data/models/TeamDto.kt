package com.example.data.models

data class TeamDto(
    val id: Int,
    val name: String,
    val description: String,
    val maxUsersCount: Int,
    val avatarUrl: String?
 )
