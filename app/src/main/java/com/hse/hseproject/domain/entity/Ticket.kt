package com.hse.hseproject.domain.entity



data class Ticket (
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