package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object CardSpells: IntIdTable() {
    val fraction = reference("fraction", Fractions)
    val name = varchar("name", 256)
    val flavor = text("flavor")
    val description = text("description")
    val imageUrl = varchar("image_url", 512).nullable()
}