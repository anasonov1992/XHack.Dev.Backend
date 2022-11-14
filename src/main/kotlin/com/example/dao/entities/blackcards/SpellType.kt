package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.SpellTypes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SpellType(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SpellType>(SpellTypes)

    var type by SpellTypes.type
    var displayName by SpellTypes.displayName
}