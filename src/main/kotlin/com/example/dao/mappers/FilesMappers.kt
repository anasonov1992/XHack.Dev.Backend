package com.example.dao.mappers

import com.example.dao.entities.blackcards.File
import com.example.data.models.FileDto

fun File.toFileDto() = FileDto(guid, name, extension, path)