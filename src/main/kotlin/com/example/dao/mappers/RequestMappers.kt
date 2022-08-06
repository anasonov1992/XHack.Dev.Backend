package com.example.dao.mappers

import com.example.dao.entities.Request
import com.example.data.models.RequestDto
import com.example.primitives.RequestType

fun Request.toRequestDto(): RequestDto = when(type) {
    RequestType.TeamToUser -> toTeamRequestDto()
    RequestType.UserToTeam -> toUserRequestDto()
    else -> RequestDto(id.value, null, null, type, isCanceled)
}

fun Request.toTeamRequestDto(): RequestDto = RequestDto(id.value, null, fromTeam?.toTeamDto(), type, isCanceled)

fun Request.toUserRequestDto(): RequestDto = RequestDto(id.value, fromUser?.toUserDto(), null, type, isCanceled)