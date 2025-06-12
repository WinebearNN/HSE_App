package com.hse.hseproject.domain.repository

import com.hse.hseproject.domain.entity.Event

interface EventRepository {

    suspend fun getEventByGlobalId(eventGlobalId:String) : Result<Event>

    suspend fun getAllEvents(): Result<List<Event>>

}