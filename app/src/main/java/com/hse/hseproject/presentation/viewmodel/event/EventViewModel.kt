package com.hse.hseproject.presentation.viewmodel.event

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hse.hseproject.domain.entity.Ticket
import com.hse.hseproject.domain.useCase.event.EventUseCase
import com.hse.hseproject.domain.useCase.ticket.TicketUseCase
import com.hse.hseproject.presentation.viewmodel.ticket.TicketResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.timerTask

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventUseCase: EventUseCase,
    private val ticketUseCase: TicketUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "EventViewModel"
    }


    private val _eventGetResult = MutableStateFlow<EventResult>(EventResult.Loading)
    val eventGetResult: StateFlow<EventResult> = _eventGetResult.asStateFlow()

    private val _eventsAllResult = MutableStateFlow<EventResult>(EventResult.Loading)
    val eventsAllResult: StateFlow<EventResult> = _eventsAllResult.asStateFlow()

    private val _ticketCreateResult = MutableStateFlow<TicketResult>(TicketResult.Loading)
    val ticketCreateResult: StateFlow<TicketResult> = _ticketCreateResult.asStateFlow()

    private val _ticketDeleteResult = MutableStateFlow<TicketResult>(TicketResult.Loading)
    val ticketDeleteResult: StateFlow<TicketResult> = _ticketDeleteResult.asStateFlow()

    private val _userGlobalId = MutableStateFlow<String>("")
    val userGlobalId: StateFlow<String> = _userGlobalId

    fun loadData(context: Context,eventGlobalId: String) {
        viewModelScope.launch {
            _eventGetResult.value = EventResult.Loading
            runCatching {
                val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
                _userGlobalId.value = prefs.getString("userGlobalId", "12232") ?: "12232"
            }.onSuccess {
                if (_userGlobalId.value.isBlank()) {
                    _eventGetResult.value =
                        EventResult.Error(Exception("Пользователь не найден"))
                } else {
                    getEventByGlobalId(eventGlobalId)
                }
            }.onFailure { e ->
                _eventGetResult.value = EventResult.Error(e)
            }
        }
    }

    private fun getEventByGlobalId(eventGlobalId: String) {
        viewModelScope.launch {
            _eventGetResult.value = EventResult.Loading
            eventUseCase.getEventByGlobalId(
                eventGlobalId = eventGlobalId
            ).fold(
                onSuccess = { event ->
                    _eventGetResult.value = EventResult.EventGetSuccess(event)
                },
                onFailure = { e ->
                    _eventGetResult.value = EventResult.Error(e)
                }
            )
        }
    }

    fun getAllEvents() {
        viewModelScope.launch {
            _eventsAllResult.value = EventResult.Loading
            eventUseCase.getAllEvents()
                .fold(
                    onSuccess = { events ->
                        _eventsAllResult.value = EventResult.EventAllSuccess(events)
                    },
                    onFailure = { e ->
                        _eventsAllResult.value = EventResult.Error(e)
                    }
                )
        }
    }

    fun create(ticket: Ticket) {
        _ticketCreateResult.value = TicketResult.Loading
        viewModelScope.launch {
            delay(3000L)
            ticketUseCase.create(
                ticket = ticket
            ).fold(
                onSuccess = { result ->
                    _ticketCreateResult.value = TicketResult.TicketCreateSuccess(result)
                },
                onFailure = { e ->
                    _ticketCreateResult.value = TicketResult.Error(e)
                }
            )
        }
    }

    fun delete(ticket: Ticket) {
        viewModelScope.launch {
            _ticketDeleteResult.value = TicketResult.Loading
            ticketUseCase.delete(
                ticket = ticket
            ).fold(
                onSuccess = { result ->
                    _ticketDeleteResult.value = TicketResult.TicketDeleteSuccess(result)
                },
                onFailure = { e ->
                    _ticketDeleteResult.value = TicketResult.Error(e)
                }
            )
        }
    }




}