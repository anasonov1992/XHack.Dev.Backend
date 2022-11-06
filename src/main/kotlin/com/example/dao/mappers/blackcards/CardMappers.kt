package com.example.dao.mappers.blackcards

import com.example.dao.entities.blackcards.Card
import com.example.dao.entities.blackcards.CardUnit
import com.example.dao.entities.blackcards.Rank
import com.example.dao.entities.blackcards.UnitClass
import com.example.data.models.blackcards.*

fun Card.toCardArtDto(): CardArtDto {
    return CardArtDto(id.value, name, artUrlList, fraction.name)
}

fun Card.toCardArtDetailDto(): CardArtDetailDto {
    return CardArtDetailDto(id.value, name, description, artUrlDetail, fraction.name)
}

fun Rank.toRankDto() = RankDto(id.value, displayName)

fun UnitClass.toUnitClassDto() = UnitClassDto(id.value, type, displayName)

fun CardUnit.toCardUnitDto() = CardUnitDto(
    id.value, rank.toRankDto(), name, isUnique, isNotMoreTwo, unitClasses.map { it.toUnitClassDto() }, flavor, description, imageUrl)