package com.example.routes.auth

import com.example.features.auth.AuthController
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.register(tokenConfig: TokenConfig) {
    post("/api/register") {
        val controller = AuthController(call)
        controller.register(tokenConfig)
    }
}
