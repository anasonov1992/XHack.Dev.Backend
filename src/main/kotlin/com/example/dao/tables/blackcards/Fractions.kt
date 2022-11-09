package com.example.dao.tables.blackcards

import com.example.data.primitives.blackcards.FractionType
import org.jetbrains.exposed.dao.id.IntIdTable

object Fractions : IntIdTable() {
    val type = enumeration<FractionType>("type").nullable()
    val name = varchar("name", 128)
    val description = text("description")
    val artUrl = varchar("art_url", 512).nullable()
}