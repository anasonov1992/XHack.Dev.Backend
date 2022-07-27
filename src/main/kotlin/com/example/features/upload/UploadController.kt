package com.example.features.upload

import com.example.data.responses.UploadFileResponse
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.io.File

class UploadController(private val call: ApplicationCall) {
    suspend fun upload() {
        var fileDescription = ""
        var fileName = ""
        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    fileDescription = part.value
                }
                is PartData.FileItem -> {
                    fileName = part.originalFileName as String
                    var fileBytes = part.streamProvider().readBytes()
                    File("uploads/$fileName").writeBytes(fileBytes)
                }
                else -> {
                    call.respond(HttpStatusCode.BadRequest, "Wrong uploaded file format")
                }
            }
        }

        call.respond(HttpStatusCode.OK, UploadFileResponse("${Constants.BASE_URL}/uploads/$fileName"))
    }
}