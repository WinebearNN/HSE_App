package com.hse.hseproject.data.network.request

import com.hse.hseproject.domain.entity.Event

data class TicketRequest(
    val ticketGlobalId:Long,
    val userGlobalId:Long,
    val event:Event,
)