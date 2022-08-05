package com.example.routes

import com.example.features.requests.RequestsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getRequests() {
    get("/api/getRequests") {
        val controller = RequestsController(call)
        controller.getRequests()
    }
}