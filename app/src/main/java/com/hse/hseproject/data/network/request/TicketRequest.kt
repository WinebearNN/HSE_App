package com.hse.hseproject.data.network.request

import com.hse.hseproject.domain.entity.Format

data class TicketRequest(
    val ticketGlobalId:Long,
    val userGlobalId:Long,
    val eventGlobalId:Long,
    val eventName:String,
    val eventCompanyName:String,
    val eventAddress: String,
    val eventDate:Long,
    val eventTimeStart:String,
    val eventFormat: Format
)