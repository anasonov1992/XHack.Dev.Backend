package com.example.dao.mappers

import com.example.dao.entities.blackcards.File
import com.example.data.models.FileDto
import com.example.data.models.FileModel

fun File.toFileDto() = FileDto(guid, name, extension)

fun File.toFileModel() = FileModel(guid, name, extension, fileBinary)