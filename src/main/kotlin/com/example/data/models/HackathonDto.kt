package com.example.data.models

import java.util.*

@kotlinx.serialization.Serializable
data class HackathonDto(
    val id: Int,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val location: String,
//    val startDate: Date,
//    val endDate: Date,
    val siteUrl: String,
    val avatarUrl: String,
//    val tags: List<TagDto>,
    val isBookmarked: Boolean
)
