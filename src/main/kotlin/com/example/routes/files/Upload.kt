package com.example.routes.files

import com.example.features.upload.UploadController
import com.example.security.token.TokenConfig
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.upload(tokenConfig: TokenConfig) {
    post("/api/upload") {
        val controller = UploadController(call)
        controller.upload()
    }
}