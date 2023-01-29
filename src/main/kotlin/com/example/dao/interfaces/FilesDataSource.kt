package com.example.dao.interfaces

import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import com.example.data.models.FileModel
import com.example.data.models.blackcards.UpdateFileDto
import com.example.data.requests.SearchRequestDto
import com.example.utils.DbResult
import java.util.UUID

interface FilesDataSource {
    suspend fun createFile(file: CreateFileDto, fileBinary: ByteArray): DbResult<FileDto>
    suspend fun updateFile(file: UpdateFileDto): DbResult<FileDto>
    suspend fun getFile(guid: UUID): FileModel?
    suspend fun getFiles(request: SearchRequestDto): List<FileDto>
    suspend fun deleteFile(guid: UUID): Unit?
}