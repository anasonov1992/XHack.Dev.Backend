package com.example.data.requests

@kotlinx.serialization.Serializable
data class PagingRequest(
    val pageSize: Int,
    val pageNumber: Long
)
