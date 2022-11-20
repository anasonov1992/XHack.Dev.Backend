package com.example.data.models

import java.util.*

class FileModel(
    val guid: UUID,
    val name: String,
    val extension: String,
    val fileBinary: ByteArray?
)