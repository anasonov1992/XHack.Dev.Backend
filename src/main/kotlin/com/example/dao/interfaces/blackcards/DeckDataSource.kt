package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.CreateDeckDto
import com.example.data.models.blackcards.DeckDto
import com.example.data.models.blackcards.DeckGroupDto
import com.example.data.models.blackcards.DeckRankGroupDto
import com.example.utils.DbResult

interface DeckDataSource {
    suspend fun getFractionCards(fractionId: Int): List<DeckRankGroupDto>

    suspend fun getDeckCards(deckId: Int, fractionId: Int): List<DeckRankGroupDto>

    suspend fun createDeck(deck: CreateDeckDto): DbResult<DeckDto>

    suspend fun getDecks(): List<DeckGroupDto>
}