package com.example.dao.interfaces

import com.example.dao.entities.Request
import com.example.data.models.RequestDto

interface RequestsDataSource {
    suspend fun getRequests(): List<Request>
    suspend fun createRequest(request: RequestDto): Request?
}