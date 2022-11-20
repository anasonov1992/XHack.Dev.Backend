package com.example.data.models

import com.example.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class FileGuidDto(
    @Serializable(with = UUIDSerializer::class)
    val guid: UUID
)
