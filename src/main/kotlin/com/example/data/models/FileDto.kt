package com.example.data.models

import com.example.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class FileDto(
    @Serializable(with = UUIDSerializer::class)
    val guid: UUID,
    val name: String,
    val extension: String,
    val path: String
)
