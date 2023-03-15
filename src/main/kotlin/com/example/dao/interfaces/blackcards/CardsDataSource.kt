package com.example.dao.interfaces.blackcards

import com.example.data.models.blackcards.*
import com.example.data.requests.SearchPagingRequestDto
import com.example.data.requests.blackcards.CardsPagingRequestDto
import com.example.utils.DbResult

interface CardsDataSource {
    suspend fun getCardArts(request: SearchPagingRequestDto): List<CardArtDto>
    suspend fun getCardArtDetail(id: Int): DbResult<CardArtDetailDto>
    suspend fun createCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDto>
    suspend fun updateCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDetailDto>
    suspend fun getRanks(): List<RankDto>
    suspend fun createRank(rank: RankDto): DbResult<RankDto>
    suspend fun updateRank(rank: RankDto): DbResult<RankDto>
    suspend fun getUnitClasses(): List<UnitClassDto>
    suspend fun createUnitClass(unitClass: UnitClassDto): DbResult<UnitClassDto>
    suspend fun getCardUnits(request: CardsPagingRequestDto): List<CardUnitDto>
    suspend fun createCardUnit(cardUnit: CreateCardUnitDto): DbResult<CardUnitDto>
    suspend fun updateCardUnit(cardUnit: CreateCardUnitDto): DbResult<CardUnitDto>
    suspend fun getSpellTypes(): List<SpellTypeDto>
    suspend fun createSpellType(spellType: SpellTypeDto): DbResult<SpellTypeDto>
    suspend fun getCardSpells(request: CardsPagingRequestDto): List<CardSpellDto>
    suspend fun createCardSpell(cardSpell: CreateCardSpellDto): DbResult<CardSpellDto>
    suspend fun updateCardSpell(cardSpell: CreateCardSpellDto): DbResult<CardSpellDto>
}