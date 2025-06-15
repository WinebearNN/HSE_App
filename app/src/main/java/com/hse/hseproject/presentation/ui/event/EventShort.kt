package com.hse.hseproject.presentation.ui.event

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.EventListener
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.crossfade
import com.hse.hseproject.domain.entity.Event
import com.hse.hseproject.domain.entity.Format
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun EventShortScreen(
    navController: NavController,
    event: Event
) {

    val colorScheme = MaterialTheme.colorScheme


    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .eventListener(object : EventListener() {
            override fun onStart(request: ImageRequest) {
                Log.d("ImageLoader", "Запрос начался: ${request.data}")
            }

            override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                Log.d("ImageLoader", "Успешно: ${request.data}")
            }

            override fun onError(request: ImageRequest, result: ErrorResult) {
                Log.e("ImageLoader", "Ошибка: ${request.data} ${result.throwable}")
            }
        })
        .build()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clickable {
                navController.navigate("event/${event.eventGlobalId}") {
                    launchSingleTop = true
                }
            }
            .background(
                color = colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 220.dp)
                .background(
                    color = colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(shape = RoundedCornerShape(12.dp)),
//                .border(
//                    width = 0.dp,
//                    color = Color.DarkGray,
//                    shape = RoundedCornerShape(12.dp)
//                ),
            clipToBounds = true,
            imageLoader = imageLoader,
            model = ImageRequest.Builder(LocalContext.current)
//                .data("https://i.imgur.com/XK5f6ex.jpeg")
//                .data("https://static.tildacdn.com/tild3762-6637-4538-b636-366465313963/HSE-17471_Preview_2_.jpg")
                .data(event.photoLinks.first())
                .crossfade(true)
                .build(),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            contentDescription = "",
//            placeholder = painterResource(id = R.drawable.placehorlder),
//            error = painterResource(id = R.drawable.placehorlder),
        )

        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 4.dp, end = 2.dp, start = 2.dp)
//                .background(
//                    color = colorScheme.primaryContainer,
//                )
                .fillMaxWidth(),
            text = event.companyName + " " + event.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .background(
                    color = colorScheme.primaryContainer,
                    shape = RoundedCornerShape(
                        bottomEnd = 10.dp, bottomStart = 10.dp
                    )
                )
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 2.dp,
                        ),
                    text = if (event.format == Format.ONLINE) Format.ONLINE.nameFormat else event.city,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 2.dp,
                            vertical = 2.dp
                        ),
                    text = event.address,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 2.dp,
                        ),
                    text = (SimpleDateFormat("dd/MM/yyyy", java.util.Locale.US)
                        .format(Date(event.date))),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 2.dp,
                            vertical = 2.dp
                        ),
                    text = event.timeStart + " - " + event.timeEnd,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    fontSize = 14.sp
                )
            }

        }


    }


}

//@Preview(showBackground = true)
//@Composable
//private fun AdvertisementShortPreview() {
//    EventShortScreen(navController = rememberNavController())
//}