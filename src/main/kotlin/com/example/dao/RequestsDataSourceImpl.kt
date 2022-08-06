package com.example.dao

import com.example.dao.DatabaseFactory.dbQuery
import com.example.dao.entities.Request
import com.example.dao.entities.Team
import com.example.dao.entities.User
import com.example.dao.interfaces.RequestsDataSource
import com.example.dao.mappers.toRequestDto
import com.example.dao.mappers.toTeamRequestDto
import com.example.dao.mappers.toUserRequestDto
import com.example.dao.tables.Requests
import com.example.data.models.InviteUserDto
import com.example.data.models.RequestDto
import com.example.primitives.RequestType

class RequestsDataSourceImpl: RequestsDataSource {
    //Use DAO
    override suspend fun getRequests(userId: Int): List<RequestDto> = dbQuery {
        Request.find { Requests.invitedUserId eq userId }.map { it.toRequestDto()}.toList()
    }

    //Use DAO
    override suspend fun createRequest(userId: Int, request: InviteUserDto): RequestDto? =
        dbQuery {
            var createdRequest: RequestDto? = null

            if (request.teamId != null) {
                createdRequest = Team.findById(request.teamId)?.let {
                    Request.new {
                        invitedUserId = userId
                        fromTeam = it
                        fromUser = null
                        type = RequestType.TeamToUser
                        isCanceled = false
                    }.toTeamRequestDto()
                }
            } else if (request.userId != null) {
                createdRequest = User.findById(request.userId)?.let {
                    Request.new {
                        invitedUserId = userId
                        fromTeam = null
                        fromUser = it
                        type = RequestType.UserToTeam
                        isCanceled = false
                    }.toUserRequestDto()
                }
            }

            return@dbQuery createdRequest
        }
}