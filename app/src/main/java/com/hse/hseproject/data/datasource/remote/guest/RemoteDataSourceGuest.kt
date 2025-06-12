package com.hse.hseproject.data.datasource.remote.guest

import android.util.Log
import com.google.gson.Gson
import com.hse.hseproject.data.datasource.remote.student.RemoteDataSourceStudent
import com.hse.hseproject.data.network.apiService.ApiServiceGuest
import com.hse.hseproject.data.network.apiService.ApiServiceStudent
import com.hse.hseproject.data.network.request.ApiRequest
import com.hse.hseproject.data.network.request.GuestRequest
import com.hse.hseproject.data.network.request.StudentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceGuest @Inject constructor(
    private val apiServiceGuest: ApiServiceGuest
) {
    companion object {
        private const val TAG = "RemoteDataSourceGuest"
    }

    suspend fun logInGuest(email: String, phoneNumber: String, name: String): Result<String> =
        withContext(Dispatchers.IO) {
            runCatching {
                val guestRequest = GuestRequest(
                    email = email,
                    phoneNumber = phoneNumber,
                    name = name
                )
                val request = ApiRequest(
                    message = Gson().toJson(guestRequest)
                )
                val response = apiServiceGuest.logIn(request)
                if (response.success) response.message else throw Exception("Authorization failed: ${response.message}")
            }.onFailure { e ->
                Log.e(
                    TAG,
                    "An error occurred during authorization",
                    e
                )
            }
        }

}