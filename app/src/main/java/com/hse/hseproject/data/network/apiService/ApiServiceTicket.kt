package com.hse.hseproject.data.network.apiService

import com.hse.hseproject.data.network.request.ApiRequest
import com.hse.hseproject.data.network.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceTicket {

    @GET("/ticket/get/all/{userGlobalId}/")
    suspend fun getTicketsByUserGlobalId(@Path("userGlobalId") userGlobalId: String): ApiResponse

    @POST("/ticket/create/")
    suspend fun create(@Body request: ApiRequest): ApiResponse

    @DELETE("/ticket/delete/")
    suspend fun delete(@Body request: ApiRequest): ApiResponse
}