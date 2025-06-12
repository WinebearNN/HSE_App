package com.hse.hseproject.data.datasource.remote.event

import android.util.Log
import com.hse.hseproject.data.network.apiService.ApiServiceEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceEvent @Inject constructor(
    private val apiServiceEvent: ApiServiceEvent
) {

    companion object {
        private const val TAG = "RemoteDataSourceEvent"
    }

    suspend fun getAllEvents(
    ): Result<String> = withContext(Dispatchers.IO) {

        runCatching {

            val response = apiServiceEvent.getAllEvents()
            response.message

        }.onFailure { e->

            Log.e(TAG,"An error occurred while getting all Events",e)
            Result.failure<Throwable>(e)

        }

    }

    suspend fun getEventByGlobalId(
        eventGlobalId: String
    ): Result<String> = withContext(Dispatchers.IO){

        runCatching {

            val response = apiServiceEvent.getEventByGlobalId(eventGlobalId)
            response.message

        }.onFailure { e->

            Log.e(TAG,"An error occurred while getting Event by Id",e)
            Result.failure<Throwable>(e)

        }

    }
}