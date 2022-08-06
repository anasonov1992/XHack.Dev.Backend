package com.example.dao.interfaces

import com.example.data.models.InviteUserDto
import com.example.data.models.RequestDto

interface RequestsDataSource {
    suspend fun getRequests(userId: Int): List<RequestDto>
    suspend fun createRequest(userId: Int, request: InviteUserDto): RequestDto?
}