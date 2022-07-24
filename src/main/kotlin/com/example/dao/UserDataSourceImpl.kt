package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.interfaces.UserDataSource
import com.example.dao.models.User
import com.example.dao.tables.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDataSourceImpl : UserDataSource {
    private fun resultRowToNode(row: ResultRow) = User(
        id = row[Users.id],
        username = row[Users.username],
        password = row[Users.password],
        salt = row[Users.salt]
    )

    override suspend fun createUser(user: User): User? = dbQuery{
        val insertStatement = Users.insert {
            it[username] = user.username
            it[password] = user.password
            it[salt] = user.salt
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNode)
    }

    override suspend fun getByUsername(username: String): User? = dbQuery {
        Users
            .select { Users.username eq username }
            .map(::resultRowToNode).singleOrNull()
    }
}