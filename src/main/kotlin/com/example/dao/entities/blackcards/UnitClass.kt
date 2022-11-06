package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.UnitClasses
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UnitClass(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UnitClass>(UnitClasses)

    var displayName by UnitClasses.displayName
    var type by UnitClasses.type
}