package com.example.dao.tables

import org.jetbrains.exposed.sql.Table

object Users: Table(){
    val id = integer("id").autoIncrement()
    val username = varchar("username", 128).uniqueIndex()
    val password = varchar("password", 128)
    val salt = varchar("salt", 128)
    override val primaryKey = PrimaryKey(id)
}