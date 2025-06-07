package com.hse.hseproject.data.network.apiService

import com.hse.hseproject.data.network.request.StudentRequest
import com.hse.hseproject.data.network.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceStudent {

    @POST("/student/logIn/")
    suspend fun logIn(@Body request: StudentRequest): ApiResponse

//    @GET("user/get/{email}")
//    suspend fun getUserByEmail(@Path("email") email: String): ApiResponse

}