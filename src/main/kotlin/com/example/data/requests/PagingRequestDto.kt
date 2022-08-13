package com.example.data.requests

@kotlinx.serialization.Serializable
data class PagingRequestDto(
    val pageSize: Int,
    val pageNumber: Long
)
