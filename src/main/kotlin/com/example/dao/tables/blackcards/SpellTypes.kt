package com.example.dao.tables.blackcards

import com.example.data.primitives.blackcards.SpellType
import org.jetbrains.exposed.dao.id.IntIdTable

object SpellTypes: IntIdTable() {
    val type = enumeration<SpellType>("type")
    val displayName = varchar("display_name", 128)
}