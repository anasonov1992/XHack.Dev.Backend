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
        var fileName = ""
        val multipart = call.receiveMultipart()

        multipart.forEachPart { part ->
            // if part is a file (could be form item)
            if(part is PartData.FileItem) {
                // retrieve file name of upload
                fileName = part.originalFileName!!
                val file = File("/${Constants.UPLOAD_PATH}}/${fileName}")

                // use InputStream from part to save file
                part.streamProvider().use { its ->
                    // copy the stream to the file with buffering
                    file.outputStream().buffered().use {
                        // note that this is blocking
                        its.copyTo(it)
                    }
                }
            }
            // make sure to dispose of the part after use to prevent leaks
            part.dispose()
        }

        val filePath = "${Constants.BASE_URL}/${Constants.UPLOAD_PATH}/${fileName}"
        
        call.respond(HttpStatusCode.OK, UploadFileResponse(filePath))

//        var fileDescription = ""
//        var filePath = ""
//        val multipartData = call.receiveMultipart()
//
//        multipartData.forEachPart { part ->
//            when (part) {
//                is PartData.FormItem -> {
//                    fileDescription = part.value
//                }
//                is PartData.FileItem -> {
//                    var fileBytes = part.streamProvider().readBytes()
//                    val fileExtension = part.originalFileName?.takeLastWhile { it != '.' }
//                    val fileName = "${UUID.randomUUID()}.$fileExtension"
//                    val filePath = "${Constants.BASE_URL}/${Constants.UPLOAD_PATH}/${fileName}"
//                    File(filePath).writeBytes(fileBytes)
//                }
//                else -> {
//                    call.respond(HttpStatusCode.BadRequest, "Wrong uploaded file format")
//                }
//            }
//        }
    }
}