package com.example.dao.entities.blackcards

import com.example.dao.tables.blackcards.CardSpells
import com.example.dao.tables.blackcards.CardsSpellTypes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CardSpell(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CardSpell>(CardSpells)

    var fraction by Fraction referencedOn CardSpells.fraction
    var name by CardSpells.name
    var spellTypes by SpellType via CardsSpellTypes
    var flavor by CardSpells.flavor
    var description by CardSpells.description
    var imageUrl by CardSpells.imageUrl
}