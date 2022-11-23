package com.example.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequestDto(
    val filter: String = ""
)
