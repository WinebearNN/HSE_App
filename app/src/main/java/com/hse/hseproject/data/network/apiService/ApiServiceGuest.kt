package com.hse.hseproject.data.network.apiService

import com.hse.hseproject.data.network.request.GuestRequest
import com.hse.hseproject.data.network.request.StudentRequest
import com.hse.hseproject.data.network.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceGuest {

    @POST("/guest/logIn/")
    suspend fun logIn(@Body request: GuestRequest): ApiResponse

}