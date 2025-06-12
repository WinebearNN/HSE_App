package com.hse.hseproject.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hse.hseproject.data.datasource.remote.event.RemoteDataSourceEvent
import com.hse.hseproject.data.network.response.EventResponse
import com.hse.hseproject.domain.entity.Event
import com.hse.hseproject.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val remoteDataSourceEvent: RemoteDataSourceEvent
) : EventRepository {

    companion object {
        private const val TAG = "EventRepositoryImpl"
    }

    override suspend fun getEventByGlobalId(eventGlobalId: String): Result<Event> {
        val result = remoteDataSourceEvent.getEventByGlobalId(
            eventGlobalId = eventGlobalId
        )

        if (result.isSuccess) {

            val resultEvent = result.getOrElse { e ->
                Log.e(TAG, "Event from server is null")
                return Result.failure(e)
            }
            val eventResponse = Gson().fromJson<EventResponse>(
                resultEvent,
                EventResponse::class.java
            )

            val event = Event(
                eventGlobalId = eventResponse.eventGlobalId,
                name = eventResponse.name,
                companyName = eventResponse.companyName,
                description = eventResponse.description,
                photoLinks = eventResponse.photoLinks,
                city = eventResponse.city,
                address = eventResponse.address,
                date = eventResponse.date,
                duration = eventResponse.duration,
                timeStart = eventResponse.timeStart,
                timeEnd = eventResponse.timeEnd,
                format = eventResponse.format
            )

            Log.d(TAG, "Event from server: $event")

            return Result.success(event)
        } else {
            Log.e(TAG, "An error occurred while getting Event by Id: ${result.getOrNull()}")
            return Result.failure(
                Exception("An error occurred while getting Event by Id: ${result.getOrNull()}")
            )
        }

    }

    override suspend fun getAllEvents(): Result<List<Event>> {
        val result = remoteDataSourceEvent.getAllEvents()

        if (result.isSuccess) {

            val resultEvents = result.getOrElse { e ->
                Log.e(TAG, "Event list from server is null")
                return Result.failure(e)
            }

            val eventsResponse = Gson().fromJson<List<EventResponse>>(
                resultEvents,
                object : TypeToken<List<EventResponse>>() {}.type
            )

            val eventList: MutableList<Event> = mutableListOf()

            eventsResponse.forEach { eventResponse ->
                eventList.add(
                    Event(
                        eventGlobalId = eventResponse.eventGlobalId,
                        name = eventResponse.name,
                        companyName = eventResponse.companyName,
                        description = eventResponse.description,
                        photoLinks = eventResponse.photoLinks,
                        city = eventResponse.city,
                        address = eventResponse.address,
                        date = eventResponse.date,
                        duration = eventResponse.duration,
                        timeStart = eventResponse.timeStart,
                        timeEnd = eventResponse.timeEnd,
                        format = eventResponse.format
                    )
                )
            }

            Log.d(TAG, "Events from server: $eventList")

            return Result.success(eventList)

        } else {
            Log.e(TAG, "An error occurred while getting Events: ${result.getOrNull()}")
            return Result.failure(
                Exception("An error occurred while getting Events: ${result.getOrNull()}")
            )
        }
    }
}