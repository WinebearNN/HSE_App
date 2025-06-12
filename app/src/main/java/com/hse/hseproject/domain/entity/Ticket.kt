package com.hse.hseproject.domain.entity

data class Ticket (
    val ticketGlobalId:Long,
    val userGlobalId:Long,
    val event:Event,
)