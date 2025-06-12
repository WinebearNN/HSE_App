package com.hse.hseproject.data.network.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiServiceProvider {

    private const val BASE_URL = "http://192.168.1.55:8080/" //EXAMPLE


    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServiceStudent: ApiServiceStudent by lazy {
        retrofit.create(ApiServiceStudent::class.java)
    }

    val apiServiceGuest: ApiServiceGuest by lazy {
        retrofit.create(ApiServiceGuest::class.java)
    }

    val apiServiceEvent: ApiServiceEvent by lazy {
        retrofit.create(ApiServiceEvent::class.java)
    }

    val apiServiceTicket: ApiServiceTicket by lazy {
        retrofit.create(ApiServiceTicket::class.java)
    }



}