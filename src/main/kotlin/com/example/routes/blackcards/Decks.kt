package com.example.routes.blackcards

import com.example.controllers.blackcards.DecksController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getFractionCards() {
    post("/api/getFractionCards") {
        val controller = DecksController(call)
        controller.getFractionCards()
    }
}

fun Route.getDeckCards() {
    post("/api/getDeckCards") {
        val controller = DecksController(call)
        controller.getDeckCards()
    }
}

fun Route.createDeck() {
    post("/api/createDeck") {
        val controller = DecksController(call)
        controller.createDeck()
    }
}

fun Route.getDecks() {
    post("/api/getDecks") {
        val controller = DecksController(call)
        controller.getDecks()
    }
}