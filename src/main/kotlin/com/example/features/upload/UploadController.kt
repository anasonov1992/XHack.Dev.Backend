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
        var filePath = ""
        var fileName = ""
        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    if (part.originalFileName == null) {
                        call.respond(HttpStatusCode.BadRequest, "Missing uploaded file name")
                    }
                    else {
                        part.streamProvider().use {
                            var fileBytes = it.readBytes()
                            fileName = part.originalFileName!!
                            filePath = "${Constants.UPLOAD_PATH}"

                            println("fileName: $fileName")

                            val folder = File(filePath)
                            if (!folder.exists()) {
                                println("filePath: $filePath doesn't exist")
                                folder.mkdir()
                                println("filePath: $filePath exists: ${folder.exists()}")
                            }

                            val uploadedFile = File("${filePath}/${fileName}")
                            uploadedFile.writeBytes(fileBytes)
                        }
                    }

                    part.dispose()
                }
                else -> {
                    call.respond(HttpStatusCode.BadRequest, "Wrong uploaded file format")
                }
            }
        }

        call.respond(HttpStatusCode.OK, UploadFileResponse("${Constants.BASE_URL}${filePath}/${fileName}"))
    }
}