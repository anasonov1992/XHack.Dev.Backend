package com.example.controllers

import com.example.dao.interfaces.FilesDataSource
import com.example.data.models.CreateFileDto
import com.example.utils.DbResult
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent

class UploadController(private val call: ApplicationCall) {
    private val filesDataSource by KoinJavaComponent.inject<FilesDataSource>(FilesDataSource::class.java)

    suspend fun upload() {
        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    if (part.originalFileName == null) {
                        call.respond(HttpStatusCode.BadRequest, "Missing uploaded file name")
                        return@forEachPart
                    } else {
                        part.streamProvider().use { fileStream ->
                            part.originalFileName?.let { fileName ->
                                val fileNameParts = fileName.split('.')
                                if (fileNameParts.count() > 1) {
                                    var fileBytes = fileStream.readBytes()

                                    when (val dbResult = filesDataSource.createFile(
                                        CreateFileDto(fileNameParts[0], fileNameParts[1]),
                                        fileBytes
                                    )) {
                                        is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
                                        else -> call.respond(
                                            HttpStatusCode.InternalServerError,
                                            "An error happens when creating a file"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    part.dispose()
                }

                else -> Unit
            }
        }

        call.respond(HttpStatusCode.BadRequest, "Wrong uploaded file format")
    }
}