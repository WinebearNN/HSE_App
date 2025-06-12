package com.hse.hseproject.data.datasource.remote.student

import android.util.Log
import com.google.gson.Gson
import com.hse.hseproject.data.network.apiService.ApiServiceStudent
import com.hse.hseproject.data.network.request.ApiRequest
import com.hse.hseproject.data.network.request.StudentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceStudent @Inject constructor(
    private val apiServiceStudent: ApiServiceStudent
) {

    companion object {
        private const val TAG = "RemoteDataSourceStudent"
    }

    suspend fun logInStudent(email:String,password:String): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val studentRequest = StudentRequest(
                email = email,
                password = password,
            )

            val request = ApiRequest(
                message = Gson().toJson(studentRequest)
            )
            val response = apiServiceStudent.logIn(request)
            if (response.success) response.message else throw Exception("Authorization failed: ${response.message}")
        }.onFailure { e ->
            Log.e(TAG, "An error occurred during authorization", e)
        }
    }

}