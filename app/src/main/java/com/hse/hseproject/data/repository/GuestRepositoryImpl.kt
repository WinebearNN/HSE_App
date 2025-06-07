package com.hse.hseproject.data.repository

import com.google.gson.Gson
import com.hse.hseproject.data.datasource.local.guest.LocalDataSourceGuest
import com.hse.hseproject.data.datasource.remote.guest.RemoteDataSourceGuest
import com.hse.hseproject.data.network.response.GuestResponse
import com.hse.hseproject.data.network.response.StudentResponse
import com.hse.hseproject.domain.entity.Guest
import com.hse.hseproject.domain.repository.GuestRepository
import javax.inject.Inject

class GuestRepositoryImpl @Inject constructor(
    private val remoteDataSourceGuest: RemoteDataSourceGuest,
    private val localDataSourceGuest: LocalDataSourceGuest
) : GuestRepository {

    companion object {
        private const val TAG = "GuestRepositoryImpl"
    }

    override suspend fun logIn(
        email: String,
        phoneNumber: String,
        name: String
    ): Result<Unit> {
        val result = remoteDataSourceGuest.logInGuest(email, phoneNumber, name)
        if (result.isSuccess) {
            val guestResponse = Gson().fromJson<GuestResponse>(
                result.getOrNull()!!,
                StudentResponse::class.java
            )
            val guest = Guest(
                email = guestResponse.email,
                phoneNumber = guestResponse.phoneNumber,
                name = guestResponse.name
            )
            localDataSourceGuest.saveGuest(guest)
            return Result.success(Unit)
        }
        return Result.failure(Exception(result.getOrNull()))
    }

    override suspend fun authentication(): Result<Guest> {
        return localDataSourceGuest.getGuest()?.let {
            Result.success(it)
        }
            ?: Result.failure(Exception("Guest not auth before"))
    }

}