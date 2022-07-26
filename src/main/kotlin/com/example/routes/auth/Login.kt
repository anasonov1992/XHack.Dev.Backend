package com.example.routes.auth

import com.example.features.auth.AuthController
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.login(tokenConfig: TokenConfig) {
    post("/api/login") {
        val controller = AuthController(call)
        controller.login(tokenConfig)
    }
}