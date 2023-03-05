package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.CardsInDeck
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CardInDeck(id: EntityID<Int>) : IntEntity(id)  {
    companion object : IntEntityClass<CardInDeck>(CardsInDeck)

    var card by CardUnit referencedOn CardsInDeck.card
    var deck by Deck referencedOn CardsInDeck.deck
    var amountInDeck by CardsInDeck.amountInDeck
}