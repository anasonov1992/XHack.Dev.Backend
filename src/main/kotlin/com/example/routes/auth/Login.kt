package com.example.routes.auth

import com.example.features.auth.AuthController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.login() {
    post("/login") {
        val controller = AuthController(call)
        controller.login()
    }
}