package com.example.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class SearchPagingRequestDto(
    val filter: String? = null,
    val pageSize: Int,
    val pageNumber: Long
)
