package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.File
import com.example.dao.interfaces.FilesDataSource
import com.example.dao.mappers.toFileDto
import com.example.dao.mappers.toFileModel
import com.example.dao.tables.blackcards.Files
import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import com.example.data.models.FileModel
import com.example.data.requests.SearchRequestDto
import com.example.utils.DbResult
import org.jetbrains.exposed.sql.lowerCase
import java.util.*

class FilesDataSourceImpl : FilesDataSource {
    override suspend fun createFile(file: CreateFileDto, fileBytes: ByteArray): DbResult<FileDto> = dbQuery {
        try {
            DbResult.Success(
                File.new {
                    guid = UUID.randomUUID()
                    name = file.name
                    extension = file.extension
                    path = ""
                    fileBinary = fileBytes
                }.toFileDto()
            )
        }
        catch (ex: Exception) {
            DbResult.Conflict
        }
    }

    override suspend fun getFile(guid: UUID): FileModel? = dbQuery {
        File.find { Files.guid eq guid }.firstOrNull()?.toFileModel()
    }

    override suspend fun getFiles(request: SearchRequestDto): List<FileDto> = dbQuery {
        request.run {
            File.find { Files.name.lowerCase() like "%${request.filter}%" }
                .map { it.toFileDto() }
        }
    }

    override suspend fun deleteFile(guid: UUID) = dbQuery {
        val file = File.find { Files.guid eq guid }.firstOrNull() ?: return@dbQuery null
        file.delete()
    }
}