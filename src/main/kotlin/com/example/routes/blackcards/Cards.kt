package com.example.routes.blackcards

import com.example.controllers.blackcards.CardsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getCardArts() {
    post("/api/getCardArts") {
        val controller = CardsController(call)
        controller.getCardArts()
    }
}

fun Route.createCardArt() {
    post("/api/createCardArt") {
        val controller = CardsController(call)
        controller.createCardArt()
    }
}