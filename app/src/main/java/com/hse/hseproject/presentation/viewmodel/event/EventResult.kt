package com.hse.hseproject.presentation.viewmodel.event

import com.hse.hseproject.domain.entity.Event

sealed class EventResult {

    data class EventGetSuccess(val event: Event) : EventResult()
    data class EventAllSuccess(val events:List<Event>) : EventResult()
    data class Error(val exception: Throwable) : EventResult()
    object Loading : EventResult()

}