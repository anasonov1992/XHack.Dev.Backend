package com.example.dao.interfaces

import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import com.example.data.models.blackcards.SpellTypeDto
import java.util.UUID

interface FilesDataSource {
    suspend fun add(file: CreateFileDto) : FileDto
    suspend fun get(guid: UUID) : FileDto?
    suspend fun getFiles(): List<FileDto>
}