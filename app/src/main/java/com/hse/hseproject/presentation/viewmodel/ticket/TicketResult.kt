package com.hse.hseproject.presentation.viewmodel.ticket

import com.hse.hseproject.domain.entity.Ticket

sealed class TicketResult {

    data class TicketsGetByUGIDSuccess(val tickets: List<Ticket>) : TicketResult()
    data class TicketCreateSuccess(val unit: Unit) : TicketResult()
    data class TicketDeleteSuccess(val unit: Unit) : TicketResult()
    data class Error(val exception: Throwable) : TicketResult()
    object Loading : TicketResult()

}