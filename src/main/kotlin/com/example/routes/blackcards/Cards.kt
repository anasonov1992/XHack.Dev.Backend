package com.example.routes.blackcards

import com.example.controllers.blackcards.CardsController
import com.example.controllers.blackcards.RanksController
import com.example.controllers.blackcards.SpellTypesController
import com.example.controllers.blackcards.UnitClassesController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getCardArts() {
    post("/api/getCardArts") {
        val controller = CardsController(call)
        controller.getCardArts()
    }
}

fun Route.getCardArtDetail() {
    get("/api/getCardArtDetail/{id}") {
        val controller = CardsController(call)
        controller.getCardArtDetail()
    }
}

fun Route.createCardArt() {
    post("/api/createCardArt") {
        val controller = CardsController(call)
        controller.createCardArt()
    }
}

fun Route.updateCardArt() {
    post("/api/updateCardArt") {
        val controller = CardsController(call)
        controller.updateCardArt()
    }
}

fun Route.getCardUnits() {
    post("/api/getCardUnits") {
        val controller = CardsController(call)
        controller.getCardUnits()
    }
}

fun Route.createCardUnit() {
    post("/api/createCardUnit") {
        val controller = CardsController(call)
        controller.createCardUnit()
    }
}

fun Route.updateCardUnit() {
    post("/api/updateCardUnit") {
        val controller = CardsController(call)
        controller.updateCardUnit()
    }
}

fun Route.getRanks() {
    get("/api/getRanks") {
        val controller = RanksController(call)
        controller.getRanks()
    }
}

fun Route.createRank() {
    post("/api/createRank") {
        val controller = RanksController(call)
        controller.createRank()
    }
}

fun Route.updateRank() {
    post("/api/updateRank") {
        val controller = RanksController(call)
        controller.updateRank()
    }
}

fun Route.getUnitClasses() {
    get("/api/getUnitClasses") {
        val controller = UnitClassesController(call)
        controller.getUnitClasses()
    }
}

fun Route.createUnitClass() {
    post("/api/createUnitClass") {
        val controller = UnitClassesController(call)
        controller.createUnitClass()
    }
}

fun Route.getCardSpells() {
    post("/api/getCardSpells") {
        val controller = CardsController(call)
        controller.getCardSpells()
    }
}

fun Route.createCardSpell() {
    post("/api/createCardSpell") {
        val controller = CardsController(call)
        controller.createCardSpell()
    }
}

fun Route.updateCardSpell() {
    post("/api/updateCardSpell") {
        val controller = CardsController(call)
        controller.updateCardSpell()
    }
}

fun Route.getSpellTypes() {
    get("/api/getSpellTypes") {
        val controller = SpellTypesController(call)
        controller.getSpellTypes()
    }
}

fun Route.createSpellType() {
    post("/api/createSpellType") {
        val controller = SpellTypesController(call)
        controller.createSpellType()
    }
}