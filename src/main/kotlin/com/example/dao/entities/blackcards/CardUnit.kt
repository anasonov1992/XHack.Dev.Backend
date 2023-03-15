package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.CardUnits
import com.example.dao.tables.blackcards.CardsUnitClasses
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CardUnit(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CardUnit>(CardUnits)

    var fraction by Fraction referencedOn CardUnits.fraction
    var rank by Rank referencedOn CardUnits.rank
    var name by CardUnits.name
    var uniqueType by CardUnits.uniqueType
    var unitClasses by UnitClass via CardsUnitClasses
    var flavor by CardUnits.flavor
    var description by CardUnits.description
    var imageUrl by CardUnits.imageUrl
    var imageFullUrl by CardUnits.imageFullUrl
}