package com.example.routes.blackcards

import com.example.controllers.blackcards.FractionsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getFractions() {
    get("/api/getFractions") {
        val controller = FractionsController(call)
        controller.getFractions()
    }
}

fun Route.createFraction() {
    post("/api/createFraction") {
        val controller = FractionsController(call)
        controller.createFraction()
    }
}