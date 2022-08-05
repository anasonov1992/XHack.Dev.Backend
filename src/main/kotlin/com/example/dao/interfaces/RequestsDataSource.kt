package com.example.dao.interfaces

import com.example.dao.entities.Request
import com.example.data.models.RequestDto
import com.example.data.requests.PagingRequest

interface RequestsDataSource {
    suspend fun getRequests(request: PagingRequest): List<Request>
    suspend fun createRequest(request: RequestDto): Request?
}