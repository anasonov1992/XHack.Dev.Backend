package com.example.routes

import com.example.controllers.TeamsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getTeams() {
    post("/api/getTeams") {
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

fun Route.addUserToTeam() {
    post("/api/addUserToTeam") {
        val controller = TeamsController(call)
        controller.addUserToTeam()
    }
}