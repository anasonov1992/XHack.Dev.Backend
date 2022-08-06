package com.example.routes

import com.example.controllers.UsersController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getUsers() {
    get("/api/getUsers") {
        val controller = UsersController(call)
        controller.getUsers()
    }
}