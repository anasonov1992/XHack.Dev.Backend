package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.*
import com.example.dao.interfaces.blackcards.CardsDataSource
import com.example.dao.mappers.blackcards.*
import com.example.dao.tables.blackcards.*
import com.example.data.models.blackcards.*
import com.example.data.requests.SearchPagingRequestDto
import com.example.data.requests.blackcards.CardsPagingRequestDto
import com.example.utils.DbResult
import org.jetbrains.exposed.sql.*

class CardsDataSourceImpl : CardsDataSource {
    override suspend fun getCardArts(request: SearchPagingRequestDto): List<CardArtDto> = dbQuery {
        val query = Fractions.innerJoin(Cards).selectAll() //

        request.filter?.let {
            query.andWhere { (Cards.name.lowerCase() like "%${request.filter}%") or (Fractions.name.lowerCase() like "%${request.filter}%") }
        }

        request.run {
            Card.wrapRows(query)
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardArtDto() }
        }
    }

    override suspend fun getCardArtDetail(id: Int): DbResult<CardArtDetailDto> = dbQuery {
        val dbCardDetail = Card.findById(id) ?: return@dbQuery DbResult.NotFound
        DbResult.Success(dbCardDetail.toCardArtDetailDto())
    }

    override suspend fun createCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDto> = dbQuery {
        val dbFraction = Fraction.findById(cardArt.fractionId) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            Card.new {
                name = cardArt.name
                description = cardArt.description
                artUrlOriginal = cardArt.artUrlOriginal
                artUrlDetail = cardArt.artUrlDetail
                artUrlList = cardArt.artUrlList
                fraction = dbFraction
            }.toCardArtDto()
        )
    }

    override suspend fun updateCardArt(cardArt: CreateCardArtDto): DbResult<CardArtDetailDto> = dbQuery {
        val dbCardArt = Card.findById(cardArt.id) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            dbCardArt.apply {
                name = cardArt.name
                description = cardArt.description
                artUrlOriginal = cardArt.artUrlOriginal
                artUrlDetail = cardArt.artUrlDetail
                artUrlList = cardArt.artUrlList
            }.toCardArtDetailDto()
        )
    }

    override suspend fun getRanks(): List<RankDto> = dbQuery {
        Rank.all().map { it.toRankDto() }
    }

    override suspend fun createRank(rank: RankDto): DbResult<RankDto> = dbQuery {
        DbResult.Success(
            Rank.new {
                type = rank.type
                displayName = rank.displayName
                maxCardsInDeckCount = rank.maxCardsOfRankCount
                cardsUpMsgText =  rank.cardsUpText
            }.toRankDto()
        )
    }

    override suspend fun updateRank(rank: RankDto): DbResult<RankDto> = dbQuery {
        val dbRank = Rank.findById(rank.id) ?: return@dbQuery DbResult.NotFound

        DbResult.Success(
            dbRank.apply {
                type = rank.type
                displayName = rank.displayName
                maxCardsInDeckCount = rank.maxCardsOfRankCount
                cardsUpMsgText =  rank.cardsUpText
            }.toRankDto()
        )
    }

    override suspend fun getUnitClasses(): List<UnitClassDto> = dbQuery {
        UnitClass.all().map { it.toUnitClassDto() }
    }

    override suspend fun createUnitClass(unitClass: UnitClassDto): DbResult<UnitClassDto> = dbQuery {
        DbResult.Success(
            UnitClass.new {
                type = unitClass.type
                displayName = unitClass.displayName
            }.toUnitClassDto()
        )
    }

    override suspend fun getCardUnits(request: CardsPagingRequestDto): List<CardUnitDto> = dbQuery {
        request.run {
            CardUnit.wrapRows(Fractions.innerJoin(CardUnits)
                .select { Fractions.id eq fractionId })
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardUnitDto() }
        }
    }

    override suspend fun createCardUnit(cardUnit: CreateCardUnitDto): DbResult<CardUnitDto> = dbQuery {
        val dbFraction = Fraction.findById(cardUnit.fractionId) ?: return@dbQuery DbResult.NotFound
        val dbRank = Rank.findById(cardUnit.rankId) ?: return@dbQuery DbResult.NotFound

        var dbUnitClasses = emptyList<UnitClass>()
        if (cardUnit.classIds.isNotEmpty()) {
            dbUnitClasses = UnitClass.find { UnitClasses.id inList cardUnit.classIds }.toList()
        }

        DbResult.Success(
            CardUnit.new {
                fraction = dbFraction
                rank = dbRank
                name = cardUnit.name
                uniqueType = cardUnit.uniqueType
                unitClasses = SizedCollection(dbUnitClasses)
                flavor = cardUnit.flavor
                description = cardUnit.description
                imageUrl = cardUnit.imageUrl
                imageFullUrl = cardUnit.imageFullUrl
            }.toCardUnitDto()
        )
    }

    override suspend fun updateCardUnit(cardUnit: CreateCardUnitDto): DbResult<CardUnitDto> = dbQuery {
        val dbCardUnit = CardUnit.findById(cardUnit.id) ?: return@dbQuery DbResult.NotFound
        val dbFraction = Fraction.findById(cardUnit.fractionId) ?: return@dbQuery DbResult.NotFound
        val dbRank = Rank.findById(cardUnit.rankId) ?: return@dbQuery DbResult.NotFound

        var dbUnitClasses = emptyList<UnitClass>()
        if (cardUnit.classIds.isNotEmpty()) {
            dbUnitClasses = UnitClass.find { UnitClasses.id inList cardUnit.classIds }.toList()
        }

        DbResult.Success(
            dbCardUnit.apply {
                fraction = dbFraction
                rank = dbRank
                name = cardUnit.name
                uniqueType = cardUnit.uniqueType
                unitClasses = SizedCollection(dbUnitClasses)
                flavor = cardUnit.flavor
                description = cardUnit.description
                imageUrl = cardUnit.imageUrl
            }.toCardUnitDto()
        )
    }

    override suspend fun getSpellTypes(): List<SpellTypeDto> = dbQuery {
        SpellType.all().map { it.toSpellTypeDto() }
    }

    override suspend fun createSpellType(spellType: SpellTypeDto): DbResult<SpellTypeDto> = dbQuery {
        DbResult.Success(
            SpellType.new {
                type = spellType.type
                displayName = spellType.displayName
            }.toSpellTypeDto()
        )
    }

    override suspend fun getCardSpells(request: CardsPagingRequestDto): List<CardSpellDto> = dbQuery {
        request.run {
            CardSpell.wrapRows(Fractions.innerJoin(CardSpells)
                .select { Fractions.id eq fractionId })
                .limit(pageSize, pageSize * pageNumber)
                .map { it.toCardSpellDto() }
        }
    }

    override suspend fun createCardSpell(cardSpell: CreateCardSpellDto): DbResult<CardSpellDto> = dbQuery {
        val dbFraction = Fraction.findById(cardSpell.fractionId) ?: return@dbQuery DbResult.NotFound

        var dbSpellTypes = emptyList<SpellType>()
        if (cardSpell.typesIds.isNotEmpty()) {
            dbSpellTypes = SpellType.find { SpellTypes.id inList cardSpell.typesIds }.toList()
        }

        DbResult.Success(
            CardSpell.new {
                fraction = dbFraction
                name = cardSpell.name
                spellTypes = SizedCollection(dbSpellTypes)
                flavor = cardSpell.flavor
                description = cardSpell.description
                imageUrl = cardSpell.imageUrl
            }.toCardSpellDto()
        )
    }

    override suspend fun updateCardSpell(cardSpell: CreateCardSpellDto): DbResult<CardSpellDto> = dbQuery {
        val dbCardSpell = CardSpell.findById(cardSpell.id) ?: return@dbQuery DbResult.NotFound
        val dbFraction = Fraction.findById(cardSpell.fractionId) ?: return@dbQuery DbResult.NotFound

        var dbSpellTypes = emptyList<SpellType>()
        if (cardSpell.typesIds.isNotEmpty()) {
            dbSpellTypes = SpellType.find { SpellTypes.id inList cardSpell.typesIds }.toList()
        }

        DbResult.Success(
            dbCardSpell.apply {
                fraction = dbFraction
                name = cardSpell.name
                spellTypes = SizedCollection(dbSpellTypes)
                flavor = cardSpell.flavor
                description = cardSpell.description
                imageUrl = cardSpell.imageUrl
            }.toCardSpellDto()
        )
    }
}