package com.example.data.responses

import com.example.data.models.HackathonDto

@kotlinx.serialization.Serializable
data class HackathonsResponseDto(
    val topHackathons: List<HackathonDto>,
    val popularHackathons: List<HackathonDto>,
    val recentHackathons: List<HackathonDto>
)
