package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Request
import com.example.dao.entities.Team
import com.example.dao.entities.User
import com.example.dao.interfaces.RequestsDataSource
import com.example.data.models.RequestDto
import com.example.primitives.RequestType

class RequestsDataSourceImpl: RequestsDataSource {
    //Use DAO
    override suspend fun getRequests(): List<Request> = dbQuery {
        Request.all().toList()
    }

    //Use DAO
    override suspend fun createRequest(request: RequestDto): Request? =
        dbQuery {
            when (request.type) {
                RequestType.UserToTeam -> {
                    request.user?.let {
                        User.findById(request.user.id)?.let {
                            Request.new {
                                user = it
                                type = request.type
                                isCanceled = request.isCanceled
                            }
                        }
                    }
                }
                RequestType.TeamToUser -> {
                    request.team?.let {
                        Team.findById(request.team.id)?.let {
                            Request.new {
                                team = it
                                type = request.type
                                isCanceled = request.isCanceled
                            }
                        }
                    }
                }
                else -> null
            }
        }
}