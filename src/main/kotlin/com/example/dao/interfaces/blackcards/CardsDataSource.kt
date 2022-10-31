package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.CardArtDetailDto
import com.example.data.models.blackcards.CardArtDto
import com.example.data.models.blackcards.CreateCardArtDto
import com.example.data.requests.PagingRequestDto
import com.example.utils.DbResult

interface CardsDataSource {
    suspend fun getCardArts(request: PagingRequestDto): List<CardArtDto>
    suspend fun getCardArtDetail(id: Int): DbResult<CardArtDetailDto>
    suspend fun createCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDto>
}