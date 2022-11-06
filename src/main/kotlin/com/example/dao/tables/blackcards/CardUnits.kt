package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object CardUnits: IntIdTable() {
    val fraction = reference("fraction", Fractions)
    val rank = reference("rank", Ranks)
    val name = varchar("name", 256)
    val isUnique = bool("isUnique")
    val isNotMoreTwo = bool("isNotMoreTwo")
    val flavor = text("flavor")
    val description = text("description")
    val imageUrl = varchar("image_url", 512).nullable()
}