package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object CardsSpellTypes: IntIdTable() {
    val cardSpell = reference("card_spell", CardSpells)
    val spellType = reference("spell_type", SpellTypes)
}