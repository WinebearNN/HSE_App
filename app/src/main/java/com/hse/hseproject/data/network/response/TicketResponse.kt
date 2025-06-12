package com.hse.hseproject.data.network.response

import com.hse.hseproject.domain.entity.Event

data class TicketResponse (
    val ticketGlobalId:Long,
    val userGlobalId:Long,
    val event:Event,
)