package com.example.controllers

import com.example.dao.interfaces.FilesDataSource
import com.example.data.models.CreateFileDto
import com.example.data.models.FileGuidDto
import com.example.data.models.FileModel
import com.example.data.models.blackcards.FractionDto
import com.example.data.models.blackcards.UpdateFileDto
import com.example.data.requests.SearchPagingRequestDto
import com.example.data.requests.SearchRequestDto
import com.example.utils.Constants
import com.example.utils.DbResult
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.apache.http.annotation.Obsolete
import org.koin.java.KoinJavaComponent
import java.io.File
import java.util.*

class FilesController(private val call: ApplicationCall) {
    private val filesDataSource by KoinJavaComponent.inject<FilesDataSource>(FilesDataSource::class.java)

    suspend fun getFiles() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val request = call.receiveOrNull<SearchRequestDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        call.respond(HttpStatusCode.OK, filesDataSource.getFiles(request))
    }


    suspend fun download() {
        val guid = call.parameters["guid"] ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        val fileData: FileModel? = filesDataSource.getFile(UUID.fromString(guid))
        if (fileData == null) {
            call.respond(HttpStatusCode.NotFound, Constants.FILE_NOT_FOUND)
            return
        }

        fileData.fileBinary?.let {
            val fileFullName = "${fileData.name}.${fileData.extension}"
            val file = File.createTempFile(fileData.name, null)
            file.writeBytes(it)

            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, fileFullName)
                    .toString()
            )
            call.respondFile(file)
            return@let
        }

        call.respond(HttpStatusCode.InternalServerError, "An error happens when downloading a file")
    }


    suspend fun updateFile() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val file = call.receiveOrNull<UpdateFileDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        when (val dbResult = filesDataSource.updateFile(file)) {
            is DbResult.NotFound -> call.respond(HttpStatusCode.NotFound, "File is not found")
            is DbResult.Conflict -> call.respond(HttpStatusCode.Conflict, "Conflict error")
            is DbResult.Success -> call.respond(HttpStatusCode.OK, dbResult.data)
        }
    }


    suspend fun deleteFile() {
        val userId = call.principal<JWTPrincipal>()?.getClaim(Constants.USER_CLAIM_NAME, String::class)
        if (userId == null) {
            call.respond(HttpStatusCode.Unauthorized, Constants.UNAUTHORIZED)
            return
        }

        val fileData = call.receiveOrNull<FileGuidDto>() ?: run {
            call.respond(HttpStatusCode.BadRequest, Constants.INVALID_REQUEST)
            return
        }

        val deletedFile = filesDataSource.deleteFile(fileData.guid)
        if (deletedFile == null) {
            call.respond(HttpStatusCode.BadRequest, Constants.FILE_NOT_FOUND)
        }

        call.respond(HttpStatusCode.OK)
    }
}