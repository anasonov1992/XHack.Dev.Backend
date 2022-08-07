package com.example.data.models

import kotlinx.datetime.Instant

@kotlinx.serialization.Serializable
data class HackathonDto(
    val id: Int,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val location: String,
//    val startDate: Instant,
//    val endDate: Instant,
    val siteUrl: String,
    val avatarUrl: String? = null,
    val isBookmarked: Boolean,
    val tags: List<TagDto>
)
