package com.example.data.models.blackcards

import com.example.utils.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UpdateFileDto(
    @Serializable(with = UUIDSerializer::class)
    val guid: UUID,
    val fractionId: Int? = null
)
