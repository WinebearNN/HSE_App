package com.hse.hseproject.presentation.ui.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hse.hseproject.domain.entity.Ticket
import com.hse.hseproject.presentation.viewmodel.ticket.TicketResult
import com.hse.hseproject.presentation.viewmodel.ticket.TicketViewModel
import kotlinx.coroutines.launch

private const val TAG = "EventScreen"

@Composable
fun TicketScreen(
    navController: NavController,
    viewModel: TicketViewModel = hiltViewModel<TicketViewModel>()
) {

    val scope = rememberCoroutineScope()

    val colorScheme = MaterialTheme.colorScheme
    val context = LocalContext.current

    val ticketResult by viewModel.ticketsGetByUGIDResult.collectAsState()
    var errorLoading by remember { mutableStateOf<Throwable?>(null) }
    var loading by remember { mutableStateOf(true) }
    var list by remember { mutableStateOf<List<Ticket>>(listOf()) }


    LaunchedEffect(ticketResult) {
        when (ticketResult) {

            is TicketResult.Error -> {
                errorLoading = (ticketResult as TicketResult.Error).exception
                loading = false
            }

            is TicketResult.Loading -> {
                loading = true
            }

            is TicketResult.TicketsGetByUGIDSuccess -> {
                list = (ticketResult as TicketResult.TicketsGetByUGIDSuccess).tickets
                loading = false
            }

            is TicketResult.TicketCreateSuccess -> TODO()
            is TicketResult.TicketDeleteSuccess -> TODO()
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.loadData(
                context = context
            )
        }
    }


    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = colorScheme.secondary
            )
        }
    } else {
        if (errorLoading != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorLoading!!.message.toString()
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorScheme.background
                    )
            ) {
                items(list.size) { ticketNum ->
                    TicketEntityScreen(
                        navController = navController,
                        ticket = list[ticketNum]
                    )
                }
            }
        }
    }


}