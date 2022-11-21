package com.example.routes

import com.example.controllers.FilesController
import com.example.controllers.UploadController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.upload() {
    post("/api/upload") {
        val controller = UploadController(call)
        controller.upload()
    }
}

fun Route.download() {
    get("/api/download/{guid}") {
        val controller = FilesController(call)
        controller.download()
    }
}

fun Route.getFiles() {
    post("/api/getFiles") {
        val controller = FilesController(call)
        controller.getFiles()
    }
}

fun Route.deleteFile() {
    delete("/api/deleteFile") {
        val controller = FilesController(call)
        controller.deleteFile()
    }
}