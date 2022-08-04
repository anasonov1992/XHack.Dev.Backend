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
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
        password = row[Users.password],
        salt = row[Users.salt]
    )

    override suspend fun createUser(user: User): User? = dbQuery{
        val insertStatement = Users.insert {
            it[firstName] = user.firstName
            it[lastName] = user.lastName
            it[email] = user.email
            it[password] = user.password
            it[salt] = user.salt
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNode)
    }

    override suspend fun getByEmail(email: String): User? = dbQuery {
        Users
            .select { Users.email eq email }
            .map(::resultRowToNode).singleOrNull()
    }
}