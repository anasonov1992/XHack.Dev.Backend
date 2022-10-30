package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.Cards
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Card(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Card>(Cards)

    var name by Cards.name
    var description by Cards.description
    var artUrl by Cards.artUrl
    var fraction by Fraction referencedOn Cards.fraction
}