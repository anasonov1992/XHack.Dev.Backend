package com.example.routes

import com.example.features.requests.RequestsController
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getRequests(tokenConfig: TokenConfig) {
    post("/api/getRequests") {
        val controller = RequestsController(call)
        controller.getRequests(tokenConfig)
    }
}