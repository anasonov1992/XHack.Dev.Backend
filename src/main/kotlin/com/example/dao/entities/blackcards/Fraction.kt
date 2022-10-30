package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.Cards
import com.example.dao.tables.blackcards.Fractions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Fraction(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Fraction>(Fractions)

    var name by Fractions.name
    var description by Fractions.description
    var artUrl by Fractions.artUrl
    val cards by Card referrersOn Cards.fraction
}