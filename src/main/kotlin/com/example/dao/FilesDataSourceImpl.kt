package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.blackcards.File
import com.example.dao.interfaces.FilesDataSource
import com.example.dao.mappers.toFileDto
import com.example.dao.tables.blackcards.Files
import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import java.util.*

class FilesDataSourceImpl : FilesDataSource {
    override suspend fun add(file: CreateFileDto): FileDto = dbQuery {
        File.new {
           guid = UUID.randomUUID()
           name = file.name
           extension = file.extension
           path = "docs/"
        }.toFileDto()
    }

    override suspend fun get(guid: UUID): FileDto? = dbQuery {
        File.find { Files.guid eq guid }.firstOrNull()?.toFileDto()
    }

    override suspend fun getFiles(): List<FileDto> = dbQuery {
        File.all().map { it.toFileDto() }
    }
}