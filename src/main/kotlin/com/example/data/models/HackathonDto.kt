package com.example.data.models

import kotlinx.datetime.LocalDateTime

@kotlinx.serialization.Serializable
data class HackathonDto(
    val id: Int,
    val name: String,
    val description: String,
    val isOnline: Boolean,
    val location: String,
    //FIXME possible fix dates
//    val startDate: LocalDateTime,
//    val endDate: LocalDateTime,
    val siteUrl: String,
    val avatarUrl: String? = null,
    val isBookmarked: Boolean,
    val tags: List<TagDto>
)
