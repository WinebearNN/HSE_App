package com.hse.hseproject.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hse.hseproject.data.datasource.remote.ticket.RemoteDataSourceTicket
import com.hse.hseproject.data.network.response.TicketResponse
import com.hse.hseproject.domain.entity.Ticket
import com.hse.hseproject.domain.repository.TicketRepository
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val remoteDataSourceTicket: RemoteDataSourceTicket
) : TicketRepository {

    companion object {
        private const val TAG = "TicketRepositoryImpl"
    }

    override suspend fun getTicketsByUserGlobalId(userGlobalId: String): Result<List<Ticket>> {
        val result = remoteDataSourceTicket.getTicketsByUserGlobalId(
            userGlobalId = userGlobalId
        )
        if (result.isSuccess) {

            val resultTickets = result.getOrElse { e ->
                Log.e(TAG, "Tickets from server is null")
                return Result.failure(e)
            }

            val ticketsResponse = Gson().fromJson<List<TicketResponse>>(
                resultTickets,
                object : TypeToken<List<TicketResponse>>() {}.type
            )

            val ticketsList: MutableList<Ticket> = mutableListOf()

            ticketsResponse.forEach { ticketResponse ->
                ticketsList.add(
                    Ticket(
                        ticketGlobalId = ticketResponse.ticketGlobalId,
                        userGlobalId = ticketResponse.userGlobalId,
                        eventGlobalId = ticketResponse.eventGlobalId,
                        eventName = ticketResponse.eventName,
                        eventCompanyName = ticketResponse.eventCompanyName,
                        eventAddress = ticketResponse.eventAddress,
                        eventDate = ticketResponse.eventDate,
                        eventTimeStart = ticketResponse.eventTimeStart,
                        eventFormat = ticketResponse.eventFormat,
                    )
                )
            }

            Log.d(TAG, "Tickets from server: $ticketsList")

            return Result.success(ticketsList)

        } else {

            Log.e(TAG, "An error occurred while getting Ticket: ${result.getOrNull()}")

            return Result.failure(
                Exception("An error occurred while getting Ticket: ${result.getOrNull()}")
            )

        }
    }

    override suspend fun create(ticket: Ticket): Result<Unit> {

        val result = remoteDataSourceTicket.ticketCreate(
            ticket = ticket
        )

        if (result.isSuccess) {

            Log.d(TAG, "Ticket was created successfully: ${result.getOrNull()} ")
            return Result.success(Unit)

        } else {

            Log.e(TAG, "An error occurred while creating Ticket: ${result.getOrNull()}")
            return Result.failure(
                Exception("An error occurred while creating Ticket: ${result.getOrNull()}")
            )

        }
    }

    override suspend fun delete(ticket: Ticket): Result<Unit> {

        val result = remoteDataSourceTicket.ticketDelete(
            ticket = ticket
        )

        if (result.isSuccess) {

            Log.d(TAG, "Ticket was deleted successfully: ${result.getOrNull()} ")
            return Result.success(Unit)

        } else {

            Log.e(TAG, "An error occurred while deleting Ticket: ${result.getOrNull()}")
            return Result.failure(
                Exception("An error occurred while deleting Ticket: ${result.getOrNull()}")
            )

        }
    }

}