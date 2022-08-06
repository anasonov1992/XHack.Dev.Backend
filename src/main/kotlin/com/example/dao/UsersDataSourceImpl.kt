package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.User
import com.example.dao.interfaces.UsersDataSource
import com.example.dao.mappers.toUserDto
import com.example.dao.tables.Users
import com.example.data.models.UserDto

class UsersDataSourceImpl : UsersDataSource {
    // Using SQL DSL
//    private fun resultRowToNode(row: ResultRow) = UserDto(
//        id = row[Users.id].value,
//        firstName = row[Users.firstName],
//        lastName = row[Users.lastName],
//        email = row[Users.email],
//        password = row[Users.password],
//        salt = row[Users.salt]
//    )

    override suspend fun getUsers(): List<UserDto> = dbQuery {
        User.all().map { it.toUserDto() }.toList()
    }

    override suspend fun getByEmail(email: String): UserDto? = dbQuery {
        // Using SQL DSL
//        Users.select { Users.email eq email }.map(::resultRowToNode).singleOrNull()

        //Use DAO
        User.find { Users.email eq email }.map { it.toUserDto() }.firstOrNull()
    }

    override suspend fun createUser(user: UserDto): UserDto = dbQuery {
        // Using SQL DSL
//        val insertStatement = Users.insert {
//            it[firstName] = user.firstName
//            it[lastName] = user.lastName
//            it[email] = user.email
//            it[password] = user.password
//            it[salt] = user.salt
//        }
//        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNode)

        //Use DAO
        User.new {
            firstName = user.firstName
            lastName = user.lastName
            email = user.email
            password = user.password
            salt = user.salt
        }.toUserDto()
    }
}