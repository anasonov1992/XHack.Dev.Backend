package com.example.routes

import com.example.controllers.HackathonsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getHackathons() {
    post("/api/getHackathons") {
        val controller = HackathonsController(call)
        controller.getHackathons()
    }
}

fun Route.createHackathon() {
    post("/api/createHackathon") {
        val controller = HackathonsController(call)
        controller.createHackathon()
    }
}

fun Route.getTags() {
    get("/api/getTags") {
        val controller = HackathonsController(call)
        controller.getTags()
    }
}

fun Route.addTag() {
    post("/api/addTag") {
        val controller = HackathonsController(call)
        controller.addTag()
    }
}