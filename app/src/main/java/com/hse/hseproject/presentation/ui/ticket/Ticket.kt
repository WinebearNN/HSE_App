package com.hse.hseproject.presentation.ui.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

private const val TAG = "EventScreen"

@Composable
fun TicketScreen(
    navController: NavController,
) {

    val colorScheme = MaterialTheme.colorScheme


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorScheme.background
            )
    ) {
        items(8) {
            TicketEntityScreen(
                colorScheme
            )
        }
    }
}