package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.Decks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Deck(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Deck>(Decks)

    var name by Decks.name
    var created by Decks.created
    var fraction by Deck referencedOn Decks.fraction
}