package com.hse.hseproject.presentation.viewmodel.ticket

import android.content.Context
import com.hse.hseproject.domain.useCase.ticket.TicketUseCase
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hse.hseproject.domain.entity.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketUseCase: TicketUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "TicketViewModel"
    }


    private val _userGlobalId = MutableStateFlow<String>("")
    val userGlobalId: StateFlow<String> = _userGlobalId

    private val _ticketsGetByUGIDResult = MutableStateFlow<TicketResult>(TicketResult.Loading)
    val ticketsGetByUGIDResult: StateFlow<TicketResult> = _ticketsGetByUGIDResult.asStateFlow()

    private val _ticketCreateResult = MutableStateFlow<TicketResult>(TicketResult.Loading)
    val ticketCreateResult: StateFlow<TicketResult> = _ticketCreateResult.asStateFlow()

    private val _ticketDeleteResult = MutableStateFlow<TicketResult>(TicketResult.Loading)
    val ticketDeleteResult: StateFlow<TicketResult> = _ticketDeleteResult.asStateFlow()

    fun loadData(context: Context) {
        viewModelScope.launch {
            _ticketsGetByUGIDResult.value = TicketResult.Loading
            runCatching {
                val prefs = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
                _userGlobalId.value = prefs.getString("userGlobalId", "") ?: ""
            }.onSuccess {
//                if (_userGlobalId.value.isBlank()) {
//                    _ticketsGetByUGIDResult.value =
//                        TicketResult.Error(Exception("Пользователь не найден"))
//                } else {
//                    getTicketsByUserGlobalId(_userGlobalId.value)
//                }
                getTicketsByUserGlobalId(_userGlobalId.value)
            }.onFailure { e ->
                _ticketsGetByUGIDResult.value = TicketResult.Error(e)
            }
        }
    }

    private fun getTicketsByUserGlobalId(userGlobalId: String) {
        viewModelScope.launch {
            _ticketsGetByUGIDResult.value = TicketResult.Loading
            ticketUseCase.getTicketsByUserGlobalId(
                userGlobalId = userGlobalId
            ).fold(
                onSuccess = { tickets ->
                    _ticketsGetByUGIDResult.value = TicketResult.TicketsGetByUGIDSuccess(tickets)
                },
                onFailure = { e ->
                    _ticketsGetByUGIDResult.value = TicketResult.Error(e)
                }
            )
        }
    }

    fun create(ticket: Ticket) {
        viewModelScope.launch {
            _ticketCreateResult.value = TicketResult.Loading
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