package com.hse.hseproject.presentation.ui.event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade


@Composable
fun EventScreen(
    navController: NavController,
    eventId:String
) {

    val colorScheme = MaterialTheme.colorScheme


    val context=LocalContext.current

        val pagerState = rememberPagerState(pageCount = {
            10
        })


//    Surface(
//        modifier = Modifier
//
//    ) {


        LazyColumn(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = colorScheme.background
                )

        ) {

            item {

                Box(modifier = Modifier.padding(top=16.dp).fillMaxSize()) {


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
                                            .data("https://static.tildacdn.com/tild3762-6637-4538-b636-366465313963/HSE-17471_Preview_2_.jpg")
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
//                        Spacer(
//                            modifier = Modifier
//                                .height(1.dp)
//                                .fillMaxWidth()
//                                .background(Color(0xFFD9D9D9))
//                        )


                        Column(
                            modifier = Modifier
//                                .padding(horizontal = 4.dp)
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
                                        text = "HSE",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 19.sp,
                                        letterSpacing = 1.sp
                                    )



                                    Text(
                                        modifier = Modifier
                                            .padding(top = 5.dp),
                                        text = "День открытых дверей.",
                                        fontSize = 16.sp,
                                        letterSpacing = 0.5.sp
                                    )


                                }
//                            Column(
//                                modifier = Modifier
//                                    .weight(1f),
//                                horizontalAlignment = Alignment.End,
//                                verticalArrangement = Arrangement.Center
//
//                            ) {
//                                Column(
//                                    modifier = Modifier.padding(top = 20.dp),
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.Center
//                                ) {
//
//
////                                    RatingStar(
////                                        modifier = Modifier
////                                            .padding(top = 5.dp, start = 20.dp),
////                                        value = 3.5f,
////                                        starSize = 16.dp,
////                                        fontSize = 14.sp,
////                                        paddingStarValue = 5.dp,
////                                        reviewQuantity = advertisement.value!!.quantityReviews
////                                    )
//                                }
//                            }
                            }


                            Button(
                                onClick = {
                                    val address = "г.Нижний Новгород пл. Минина"
                                    val latitude = 56.327402
                                    val longitude = 44.007066
//                                Log.d(
//                                    "AdvertisementScreen",
//                                    "id of advertisement is: ${advertisement.value!!.globalId}"
//                                )
//                                navController.navigate("placingOrder/${advertisement.value!!.globalId}") {
//                                    launchSingleTop = true
//                                }
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
                                text = "Продакт-менеджмент — развивающееся востребованное направление в России и за рубежом. Эксперты, способные управлять продуктом на всех этапах его жизненного цикла высоко ценятся в корпорациях и стартапах.\n" +
                                        "\n" +
                                        "Наша программа дает не только фундаментальные знания в продакт менеджменте, но и благодаря преподавателям — практикам из индустрии, учит студентов на наиболее актуальных кейсах из их бизнес-практики.",
                            )
                        }
                    }


//                    Row {
//
//                        Button(
//                            modifier = Modifier
//                                .padding(top = 5.dp, start = 15.dp)
//                                .weight(1f),
//                            contentPadding = PaddingValues(0.dp),
//                            onClick = {
//                                navController.popBackStack()
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color.Transparent
//                            )
//                        ) {
//
//                            Image(
//                                imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
//                                contentDescription = "Favourite",
//                                alignment = Alignment.CenterStart,
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .size(36.dp)
//
//                            )
//                        }
//
//                        Button(
//                            modifier = Modifier
//                                .padding(top = 5.dp, end = 15.dp)
//                                .weight(1f),
//                            contentPadding = PaddingValues(0.dp),
//                            onClick = {
//                                isFavoriteFlag = !isFavoriteFlag!!
//                                viewModel.saveFavoriteAdvertisement()
//                            },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = Color.Transparent
//                            )
//                        ) {
//
//
//                            Image(
//                                imageVector = if (isFavoriteFlag == true) ImageVector.vectorResource(
//                                    R.drawable.like_filled
//                                ) else ImageVector.vectorResource(
//                                    R.drawable.like
//                                ),
//                                contentDescription = "Favourite",
//                                alignment = Alignment.CenterEnd,
//                                modifier = Modifier
//                                    .size(36.dp)
//                                    .weight(1f)
//                            )
//                        }
//
//                    }
                }
            }
//        }
    }


//    } else {
//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            CircularProgressIndicator(
//                color = Color.Blue
//            )
//        }
//    }

}

//@Preview(showBackground = true)
//@Composable
//private fun AdvertisementPreview() {
//    EventScreen(rememberNavController())
//}


