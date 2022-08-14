package com.example.dao.mappers

import com.example.dao.entities.Team
import com.example.data.models.TeamDto

fun Team.toTeamDto(): TeamDto = TeamDto(id.value, name, description, avatarUrl, maxUsersCount, users.map { it.toUserDto() })