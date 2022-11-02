package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateFileDto(
    val name: String,
    val extension: String
)
