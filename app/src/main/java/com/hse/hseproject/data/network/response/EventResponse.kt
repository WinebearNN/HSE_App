package com.hse.hseproject.data.network.response

import com.hse.hseproject.domain.entity.Format
import kotlin.time.Duration

data class EventResponse(
    val eventGlobalId:Long,
    val name:String,
    val companyName:String,
    val description:String,
    val photoLinks:List<String>,
    val city:String,
    val address:String,
    val date:Long,
    val duration: Duration,
    val timeStart:String,
    val timeEnd:String,
    val format: Format
)