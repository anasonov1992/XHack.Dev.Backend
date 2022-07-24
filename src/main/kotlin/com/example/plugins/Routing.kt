package com.example.plugins

import com.example.routes.auth.login
import com.example.routes.auth.register
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World")
        }
        get("/test") {
            call.respondText("Testing...")
        }
        login()
        register()
    }
}
