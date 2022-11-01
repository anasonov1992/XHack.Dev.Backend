package com.example.dao.tables.blackcards

import org.jetbrains.exposed.dao.id.IntIdTable

object Cards : IntIdTable() {
    val name = varchar("name", 128)
    val description = text("description")
    val artUrlOriginal = varchar("art_url_original", 512).nullable()
    val artUrlDetail = varchar("art_url_detail", 512).nullable()
    val artUrlList = varchar("art_url_list", 512).nullable()
    val fraction = reference("fraction", Fractions)
}