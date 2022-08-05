package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Request
import com.example.dao.entities.Team
import com.example.dao.entities.User
import com.example.dao.interfaces.RequestsDataSource
import com.example.data.models.RequestDto
import com.example.data.requests.PagingRequest
import com.example.primitives.RequestType

class RequestsDataSourceImpl: RequestsDataSource {
    //Use DAO
    override suspend fun getRequests(request: PagingRequest): List<Request> = dbQuery {
        request.run {
            Request.all().limit(pageSize, pageNumber).toList()
        }
    }

    //Use DAO
    override suspend fun createRequest(request: RequestDto): Request? =
        dbQuery {
            when (request.type) {
                RequestType.UserToTeam -> {
                    val requestedUser = User.findById(request.user.id)
                    requestedUser?.let {
                        Request.new {
                            user = requestedUser
                            type = request.type
                            isCanceled = request.isCanceled
                        }
                    }
                }
                RequestType.TeamToUser -> {
                    val requestedTeam = Team.findById(request.team.id)
                    requestedTeam?.let {
                        Request.new {
                            team = requestedTeam
                            type = request.type
                            isCanceled = request.isCanceled
                        }
                    }
                }
                else -> null
            }
        }
}