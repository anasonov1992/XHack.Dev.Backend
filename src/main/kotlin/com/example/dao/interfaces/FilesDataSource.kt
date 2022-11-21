package com.example.dao.interfaces

import com.example.data.models.CreateFileDto
import com.example.data.models.FileDto
import com.example.data.models.FileModel
import com.example.data.requests.SearchPagingRequestDto
import com.example.utils.DbResult
import java.util.UUID

interface FilesDataSource {
    suspend fun createFile(file: CreateFileDto, fileBinary: ByteArray) : DbResult<FileDto>
    suspend fun getFile(guid: UUID) : FileModel?
    suspend fun getFiles(request: SearchPagingRequestDto): List<FileDto>
    suspend fun deleteFile(guid: UUID): Unit?
}