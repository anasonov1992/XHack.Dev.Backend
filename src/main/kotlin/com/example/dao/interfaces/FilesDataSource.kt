package com.example.dao.interfaces

import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import java.util.UUID

interface FilesDataSource {
    suspend fun add(file: CreateFileDto) : FileDto
    suspend fun get(guid: UUID) : FileDto?
}