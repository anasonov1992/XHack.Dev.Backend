package com.example.features.upload

import com.example.data.responses.UploadFileResponse
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.io.File
import java.util.*

class UploadController(private val call: ApplicationCall) {
    suspend fun upload() {
        var fileDescription = ""
        var filePath = ""
        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    fileDescription = part.value
                }
                is PartData.FileItem -> {
                    var fileBytes = part.streamProvider().readBytes()
                    val fileExtension = part.originalFileName?.takeLastWhile { it != '.' }
                    val fileName = "${UUID.randomUUID()}.$fileExtension"
                    val filePath = "${Constants.BASE_URL}/${Constants.UPLOAD_PATH}/${fileName}"
                    File(filePath).writeBytes(fileBytes)
                }
                else -> {
                    call.respond(HttpStatusCode.BadRequest, "Wrong uploaded file format")
                }
            }
        }

        call.respond(HttpStatusCode.OK, UploadFileResponse(filePath))
    }
}