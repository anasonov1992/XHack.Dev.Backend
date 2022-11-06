package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object CardsUnitClasses: IntIdTable() {
    val cardUnit = reference("card_unit", CardUnits)
    val unitClass = reference("unit_class", UnitClasses)
}