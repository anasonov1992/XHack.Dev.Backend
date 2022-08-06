package com.example.routes

import com.example.controllers.TeamsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getTeams() {
    get("/api/getTeams") {
        val controller = TeamsController(call)
        controller.getTeams()
    }
}

fun Route.createTeam() {
    post("/api/createTeam") {
        val controller = TeamsController(call)
        controller.createTeam()
    }
}