package com.example.routes.blackcards

import com.example.controllers.blackcards.CardsController
import com.example.controllers.blackcards.RanksController
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