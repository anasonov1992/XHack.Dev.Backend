package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.Ranks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Rank(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Rank>(Ranks)

    var displayName by Ranks.displayName
}