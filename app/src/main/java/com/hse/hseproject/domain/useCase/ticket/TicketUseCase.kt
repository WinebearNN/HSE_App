package com.hse.hseproject.domain.useCase.ticket

import com.hse.hseproject.domain.entity.Ticket
import com.hse.hseproject.domain.repository.TicketRepository
import javax.inject.Inject

class TicketUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {

    companion object {
        private const val TAG = "TicketUseCase"
    }

    suspend fun getTicketsByUserGlobalId(userGlobalId: String): Result<List<Ticket>> {
        return ticketRepository.getTicketsByUserGlobalId(
            userGlobalId = userGlobalId
        )
    }

    suspend fun create(ticket: Ticket): Result<Unit> {
        return ticketRepository.create(
            ticket = ticket
        )
    }

    suspend fun delete(ticket: Ticket): Result<Unit> {
        return ticketRepository.delete(
            ticket = ticket
        )
    }

}