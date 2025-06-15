package com.hse.hseproject.data.datasource.remote.ticket

import android.util.Log
import com.google.gson.Gson
import com.hse.hseproject.data.network.apiService.ApiServiceTicket
import com.hse.hseproject.data.network.request.ApiRequest
import com.hse.hseproject.data.network.request.TicketRequest
import com.hse.hseproject.domain.entity.Format
import com.hse.hseproject.domain.entity.Ticket
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceTicket @Inject constructor(
    private val apiServiceTicket: ApiServiceTicket
) {

    companion object {
        private const val TAG = "RemoteDataSourceTicket"
    }

    suspend fun getTicketsByUserGlobalId(
        userGlobalId: String
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {
            val response = apiServiceTicket.getTicketsByUserGlobalId(userGlobalId)
            response.message
        }.onFailure { e ->
            Log.e(TAG, "An error occurred while fetching tickets", e)
            Result.failure<Throwable>(e)
        }
    }

    suspend fun ticketCreate(
        ticket: Ticket
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {


            val ticketRequest = TicketRequest(
                ticketGlobalId = ticket.ticketGlobalId,
                userGlobalId = ticket.userGlobalId,
                eventGlobalId = ticket.eventGlobalId,
                eventName = ticket.eventName,
                eventCompanyName = ticket.eventCompanyName,
                eventAddress = ticket.eventAddress,
                eventDate = ticket.eventDate,
                eventTimeStart = ticket.eventTimeStart,
                eventFormat = ticket.eventFormat,
            )

            val request = ApiRequest(
                Gson().toJson(ticketRequest)
            )

            val response = apiServiceTicket.create(request)
            response.message
        }.onFailure { e ->
            Log.e(TAG, "An error occurred while creating ticket", e)
            Result.failure<Throwable>(e)
        }
    }

    suspend fun ticketDelete(
        ticket: Ticket
    ): Result<String> = withContext(Dispatchers.IO) {
        runCatching {

            val ticketRequest = TicketRequest(
                ticketGlobalId = ticket.ticketGlobalId,
                userGlobalId = ticket.userGlobalId,
                eventGlobalId = ticket.eventGlobalId,
                eventName = ticket.eventName,
                eventCompanyName = ticket.eventCompanyName,
                eventAddress = ticket.eventAddress,
                eventDate = ticket.eventDate,
                eventTimeStart = ticket.eventTimeStart,
                eventFormat = ticket.eventFormat,
            )

            val request = ApiRequest(
                Gson().toJson(ticketRequest)
            )

            val response = apiServiceTicket.delete(request)
            response.message
        }.onFailure { e ->
            Log.e(TAG, "An error occurred while deleting ticket", e)
            Result.failure<Throwable>(e)
        }
    }

}