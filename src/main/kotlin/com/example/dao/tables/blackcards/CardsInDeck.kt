package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object CardsInDeck: IntIdTable() {
    val card = reference("card", CardUnits)
    val deck = reference("deck", Decks)
    val amountInDeck = integer("amount_in_deck")
}