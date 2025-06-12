package com.hse.hseproject.domain.repository

import com.hse.hseproject.domain.entity.Ticket

interface TicketRepository {

    suspend fun getTicketsByUserGlobalId(userGlobalId: String):Result<List<Ticket>>
    suspend fun create(ticket:Ticket): Result<Unit>
    suspend fun delete(ticket:Ticket): Result<Unit>
}