package com.hse.hseproject.domain.useCase.event

import com.hse.hseproject.domain.entity.Event
import com.hse.hseproject.domain.repository.EventRepository
import javax.inject.Inject

class EventUseCase @Inject constructor(
    private val eventRepository: EventRepository
){

    companion object{
        private const val TAG="EventUseCase"
    }

    suspend fun getEventByGlobalId(eventGlobalId: String): Result<Event> {
        return eventRepository.getEventByGlobalId(
            eventGlobalId = eventGlobalId
        )
    }

    suspend fun getAllEvents(): Result<List<Event>> {
        return eventRepository.getAllEvents()
    }
    
}