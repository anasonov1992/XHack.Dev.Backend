package com.example.controllers

import com.example.dao.interfaces.FilesDataSource
import com.example.data.models.CreateFileDto
import com.example.utils.Constants
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.koin.java.KoinJavaComponent
import java.io.File
import java.util.*

class FilesController(private val call: ApplicationCall) {
    private val filesDataSource by KoinJavaComponent.inject<FilesDataSource>(FilesDataSource::class.java)

    suspend fun addFile(){
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val file = call.receiveOrNull<CreateFileDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, filesDataSource.add(file))
    }

    suspend fun download() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val guid = call.parameters["guid"] ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        val fileData = filesDataSource.get(UUID.fromString(guid))
        if (fileData == null) {
            call.respond(HttpStatusCode.NotFound, Constants.FILE_NOT_FOUND)
            return
        }

        val fileFullName = "${fileData.name}.${fileData.extension}"
        val filePath = "files/${fileData.path}${fileFullName}"
        val file = File(filePath)

        call.response.header(
            HttpHeaders.ContentDisposition,
            ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, fileFullName)
                .toString()
        )
        call.respondFile(file)
    }
}