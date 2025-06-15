package com.hse.hseproject.data.network.response

import com.hse.hseproject.domain.entity.Format

data class TicketResponse (
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