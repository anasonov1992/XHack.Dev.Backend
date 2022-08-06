package com.example.routes

import com.example.controllers.RequestsController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.getRequests() {
    get("/api/getRequests") {
        val controller = RequestsController(call)
        controller.getRequests()
    }
}

fun Route.createRequest() {
    post("/api/sendRequest") {
        val controller = RequestsController(call)
        controller.createRequest()
    }
}