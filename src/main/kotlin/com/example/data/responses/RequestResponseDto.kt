package com.example.data.responses

import com.example.data.models.RequestDto

data class RequestResponseDto(
    val fromTeams: List<RequestDto>,
    val fromUsers: List<RequestDto>
)
