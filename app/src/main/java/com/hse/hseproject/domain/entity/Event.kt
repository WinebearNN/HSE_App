package com.hse.hseproject.domain.entity


data class Event (
    val eventGlobalId:Long,
    val name:String,
    val companyName:String,
    val description:String,
    val photoLinks:List<String>,
    val city:String,
    val address:String,
    val date:Long,
    val duration: EventDuration,
    val timeStart:String,
    val timeEnd:String,
    val format: Format
)