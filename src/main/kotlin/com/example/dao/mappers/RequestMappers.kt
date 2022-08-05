package com.example.dao.mappers

import com.example.dao.entities.Request
import com.example.data.models.RequestDto
import com.example.primitives.RequestType

fun Request.toRequestDto(): RequestDto? = when(type) {
    RequestType.TeamToUser -> RequestDto(id.value, null, team!!.toTeamDto(), type, isCanceled)
    RequestType.UserToTeam -> RequestDto(id.value, user!!.toUserDto(), null, type, isCanceled)
    else -> null
}

fun Request.toTeamRequestDto(): RequestDto = RequestDto(id.value, null, team?.toTeamDto(), type, isCanceled)

fun Request.toUserRequestDto(): RequestDto = RequestDto(id.value, user?.toUserDto(), null, type, isCanceled)