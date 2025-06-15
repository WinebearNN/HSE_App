package com.hse.hseproject.presentation.ui.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.hse.hseproject.domain.entity.Event
import com.hse.hseproject.domain.entity.Ticket
import com.hse.hseproject.presentation.viewmodel.event.EventResult
import com.hse.hseproject.presentation.viewmodel.event.EventViewModel
import com.hse.hseproject.presentation.viewmodel.ticket.TicketResult
import kotlinx.coroutines.launch


@Composable
fun EventScreen(
    eventId: String,
    viewModel: EventViewModel = hiltViewModel<EventViewModel>()
) {


    val scope = rememberCoroutineScope()

    val scrollState = rememberLazyListState(0)

    val ticketResult by viewModel.ticketCreateResult.collectAsState()
    val eventResult by viewModel.eventGetResult.collectAsState()
    var errorLoading by remember { mutableStateOf<Throwable?>(null) }
    var loading by remember { mutableStateOf(true) }
    var event by remember { mutableStateOf<Event?>(null) }
    var userGlobalId by remember { mutableStateOf("") }


    var snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }

    var enabledButton by remember { mutableStateOf(true) }

    val colorScheme = MaterialTheme.colorScheme


    val context = LocalContext.current



    if (!enabledButton) {

        LaunchedEffect(ticketResult) {
            when (ticketResult) {
                is TicketResult.Error -> {
                    scope.launch {
                        snackBarHostState.showSnackbar((ticketResult as TicketResult.Error).exception.toString())
                    }
                    enabledButton = true
                }

                TicketResult.Loading -> TODO()
                is TicketResult.TicketCreateSuccess -> {
                    scope.launch {
                        snackBarHostState.showSnackbar("Success")
                    }
                    enabledButton = true
                }

                is TicketResult.TicketDeleteSuccess -> TODO()
                is TicketResult.TicketsGetByUGIDSuccess -> TODO()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.create(
                ticket = Ticket(
                    userGlobalId = userGlobalId.toLong(),
                    ticketGlobalId = 0L,
                    eventGlobalId = event!!.eventGlobalId,
                    eventName = event!!.name,
                    eventAddress = event!!.address,
                    eventFormat = event!!.format,
                    eventDate = event!!.date,
                    eventTimeStart = event!!.timeStart,
                    eventCompanyName = event!!.companyName
                )
            )
        }

    }


    LaunchedEffect(eventResult) {
        when (eventResult) {

            is EventResult.Error -> {
                errorLoading = (eventResult as EventResult.Error).exception
                loading = false
            }

            is EventResult.Loading -> {
                loading = true
            }

            is EventResult.EventGetSuccess -> {
                event = (eventResult as EventResult.EventGetSuccess).event
                loading = false
            }

            is EventResult.EventAllSuccess -> TODO()

        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.loadData(
                context = context,
                eventGlobalId = eventId
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


            SnackbarHost(snackBarHostState)


            val pagerState = rememberPagerState { event!!.photoLinks.size }


            LazyColumn(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = colorScheme.background
                    ),
                state = scrollState

            ) {

                item {

                    Box(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxSize()
                    ) {


                        Column {
                            Box(
                                modifier = Modifier.wrapContentSize()
                            ) {

                                HorizontalPager(
                                    state = pagerState,
                                    pageSpacing = 0.dp,
//                                pageSize = PageSize.Fixed(395.dp),
//                                contentPadding = PaddingValues(horizontal = 8.dp)
                                ) { page ->

                                    Box(
                                        modifier = Modifier
                                            .height(300.dp)
                                            .wrapContentWidth()
//                                        .width(395.dp)
                                            .padding(horizontal = 8.dp, vertical = 16.dp)

                                    ) {

                                        AsyncImage(
                                            modifier = Modifier
                                                .height(300.dp)
                                                .wrapContentWidth()
//                                            .width(395.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(Color.White),
                                            model = ImageRequest.Builder(LocalContext.current)
//                                                .data("https://static.tildacdn.com/tild3762-6637-4538-b636-366465313963/HSE-17471_Preview_2_.jpg")
                                                .data(event!!.photoLinks[page])
                                                .crossfade(true)
                                                .build(),
                                            alignment = Alignment.TopCenter,
//    error = painterResource(R.drawable.placeholder_2),
//    placeholder = painterResource(R.drawable.placeholder),
                                            contentDescription = "Фото товара",
                                            contentScale = ContentScale.FillBounds,
                                        )
                                    }

                                }

                            }

                            Column(
                                modifier = Modifier
                                    .background(
                                        color = colorScheme.primaryContainer,
                                        shape = RoundedCornerShape(
                                            topStart = 16.dp,
                                            topEnd = 16.dp
                                        )
                                    )
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 16.dp,
                                            topEnd = 16.dp
                                        )
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth()

                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(
                                                colorScheme.primaryContainer
                                            )
                                    ) {

                                        Text(
                                            modifier = Modifier
                                                .padding(top = 20.dp),
                                            text = event!!.companyName,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 19.sp,
                                            letterSpacing = 1.sp
                                        )



                                        Text(
                                            modifier = Modifier
                                                .padding(top = 5.dp),
                                            text = event!!.name,
                                            fontSize = 16.sp,
                                            letterSpacing = 0.5.sp
                                        )


                                    }
                                }


                                Button(
                                    onClick = {
                                        enabledButton = !enabledButton
                                    },
                                    modifier = Modifier
                                        .padding(
                                            top = 20.dp,
                                            bottom = 40.dp,
                                            end = 16.dp,
                                            start = 16.dp
                                        )
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    enabled = enabledButton,

                                    colors = ButtonColors(

                                        containerColor = Color.Black,
                                        disabledContentColor = Color.Black,

                                        contentColor = Color.White,
                                        disabledContainerColor = Color.White

                                    ),
                                    shape = RoundedCornerShape(5.dp)
                                ) {
                                    Text(
                                        text = "Записаться"
                                    )
                                }


                                Spacer(
                                    modifier = Modifier
                                        .height(1.dp)
                                        .fillMaxWidth()
                                        .background(Color(0xFFD9D9D9))
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(
                                            top = 30.dp,
                                            start = 16.dp
                                        ),
                                    text = "О мероприятии",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 22.sp

                                )

                                Text(
                                    modifier = Modifier
                                        .padding(
                                            vertical = 15.dp,
                                            horizontal = 16.dp
                                        ),
                                    text = event!!.description
//                                    text = "Продакт-менеджмент — развивающееся востребованное направление в России и за рубежом. Эксперты, способные управлять продуктом на всех этапах его жизненного цикла высоко ценятся в корпорациях и стартапах.\n" +
//                                            "\n" +
//                                            "Наша программа дает не только фундаментальные знания в продакт менеджменте, но и благодаря преподавателям — практикам из индустрии, учит студентов на наиболее актуальных кейсах из их бизнес-практики.",
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}