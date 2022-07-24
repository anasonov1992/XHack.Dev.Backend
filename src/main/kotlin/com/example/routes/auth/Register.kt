package com.example.routes.auth

import com.example.features.auth.AuthController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.register() {
    post("/register") {
        val controller = AuthController(call)
        controller.register()
    }
}
