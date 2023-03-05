package com.example.dao.tables.blackcards

import com.example.primitives.CardUniqueType
import org.jetbrains.exposed.dao.id.IntIdTable

object CardUnits: IntIdTable() {
    val fraction = reference("fraction", Fractions)
    val rank = reference("rank", Ranks)
    val name = varchar("name", 256)
    val uniqueType = enumeration<CardUniqueType>("unique_type")
    val flavor = text("flavor")
    val description = text("description")
    val imageUrl = varchar("image_url", 512).nullable()
    val imageFullUrl = varchar("image_full_url", 512).nullable()
}