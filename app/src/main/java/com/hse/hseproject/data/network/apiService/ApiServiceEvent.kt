package com.hse.hseproject.data.network.apiService

import com.hse.hseproject.data.network.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceEvent {

    @GET("/event/get/{eventId}/")
    suspend fun getEventByGlobalId(@Path("eventId") eventId: String): ApiResponse

    @GET("/event/get/all/")
    suspend fun getAllEvents(): ApiResponse



}