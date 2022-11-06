package com.example.dao.tables.blackcards

import com.example.data.primitives.blackcards.UnitClassType
import org.jetbrains.exposed.dao.id.IntIdTable

object UnitClasses: IntIdTable() {
    val type = enumeration<UnitClassType>("type")
    val displayName = varchar("display_name", 64)
}