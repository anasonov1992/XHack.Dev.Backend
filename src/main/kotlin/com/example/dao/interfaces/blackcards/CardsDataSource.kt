package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.CardArtDto
import com.example.data.requests.PagingRequestDto

interface CardsDataSource {
    suspend fun getCardArts(request: PagingRequestDto): List<CardArtDto>
    suspend fun createCardArt(team: CardArtDto): CardArtDto
}