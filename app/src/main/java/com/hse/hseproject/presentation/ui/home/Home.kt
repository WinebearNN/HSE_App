package com.hse.hseproject.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hse.hseproject.domain.entity.Event
import com.hse.hseproject.domain.entity.EventDuration
import com.hse.hseproject.domain.entity.Format
import com.hse.hseproject.presentation.ui.event.EventShortScreen

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navController: NavController,
) {

    val eventsList=listOf(
        Event(
            eventGlobalId = 123456789,
            name = "День открытых вдверей.",
            companyName = "ВШЭ",
            description = "День открытых дверей Высшей школы экономики – это твой шанс узнать всё о ведущем вузе страны: программы обучения, поступление, стипендии, стажировки, карьера и студенческая жизнь! Встречайся с преподавателями, участвуй в мастер-классах, задавай вопросы и почувствуй атмосферу ВШЭ! Узнай, как построить успешное будущее с дипломом ВШЭ! Приходи, регистрируйся, поступай!",
            photoLinks = listOf(
                "https://www.hse.ru/data/2022/09/07/1552691052/1118х745.png",
                "https://static.tildacdn.com/tild3762-6637-4538-b636-366465313963/HSE-17471_Preview_2_.jpg",
            ),
            city = "г. Нижний Новгород",
            address = "ул. Львовская 1В",
            date = 1757548800000,
            duration = EventDuration.ONE_HOUR,
            timeStart = "10:00",
            timeEnd = "16:00",
            format = Format.IN_PERSON
        ),
        Event(
            eventGlobalId = 987654321,
            name = "Лекция. Яндекс Еда.",
            companyName = "Яндекс",
            description = "День открытых дверей Яндекса – это возможность познакомиться с одной из ведущих IT-компаний России. Узнайте о стажировках, карьерных возможностях, корпоративной культуре и проектах компании. Пообщайтесь с сотрудниками, задайте вопросы и получите советы по развитию в IT. Приходите и узнайте, как начать карьеру в Яндексе!",
            photoLinks = listOf(
                "https://dpru.obs.ru-moscow-1.hc.sbercloud.ru/images/article/2022/08/11/63bc981c-84c0-4df1-8730-4c64ca214ee8.jpg",
            ),
            city = "г. Москва",
            address = "ул. Льва Толстого, 16",
            date = 1750204800000,  // 11 сентября 2025, 00:00:00 UTC
            duration = EventDuration.TWO_HOURS,
            timeStart = "12:00",
            timeEnd = "14:00",
            format = Format.ONLINE
        )
    )
    val cities = listOf("Нижний Новгород", "Санкт Петербург", "Москва", "Пермь", "Любой")
    val formats = listOf("Очный", "Онлайн", "Любой")
    val durations = listOf("менее 1 часа", "менее 1,5 часов", "менее 2 часов", "Любой")


    var selectedCity = remember { mutableStateOf("Город") }
    var selectedFormat = remember { mutableStateOf("Формат") }
    var selectedDuration = remember { mutableStateOf("Длительность") }
    var selectedPoints = remember { mutableStateOf(false) }


    var expandedCity = remember { mutableStateOf(false) }
    var expandedDuration = remember { mutableStateOf(false) }
    var expandedFormat = remember { mutableStateOf(false) }


    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
    ) {
        item {

            Column(
                modifier = Modifier
                    .padding(
                        top = 56.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Box {

                    Text(
                        modifier = Modifier
                            .padding(
                                top = 28.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                            .clickable { expandedCity.value = true }
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .background(
                                color = colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = selectedCity.value,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = if (selectedCity.value == "Город") colorScheme.tertiary else colorScheme.onSecondaryContainer,
                    )

                    DropdownMenu(
                        expanded = expandedCity.value,
                        onDismissRequest = { expandedCity.value = false },
                        offset = DpOffset(x = 16.dp, y = 0.dp),
                        shape = RoundedCornerShape(10.dp),
                        containerColor = colorScheme.secondaryContainer,
                    ) {
                        cities.forEach { city ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = city,
                                        color = colorScheme.onSecondaryContainer,
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Start,
                                    )
                                },
                                onClick = {
                                    selectedCity.value = city
                                    expandedCity.value = false
                                }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(
                            top = 28.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    end = 8.dp
                                )
                                .clickable { expandedFormat.value = true }
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .background(
                                    color = colorScheme.secondaryContainer,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = selectedFormat.value,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = if (selectedFormat.value == "Формат") colorScheme.tertiary else colorScheme.onSecondaryContainer,
                        )

                        DropdownMenu(
                            expanded = expandedFormat.value,
                            onDismissRequest = { expandedFormat.value = false },
                            offset = DpOffset(x = 16.dp, y = 0.dp),
                            shape = RoundedCornerShape(10.dp),
                            containerColor = colorScheme.secondaryContainer,
                        ) {
                            formats.forEach { format ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = format,
                                            color = colorScheme.onSecondaryContainer,
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                        )
                                    },
                                    onClick = {
                                        selectedFormat.value = format
                                        expandedFormat.value = false
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                )
                                .clickable { expandedDuration.value = true }
                                .fillMaxWidth()
                                .wrapContentHeight(align = Alignment.CenterVertically)
                                .background(
                                    color = colorScheme.secondaryContainer,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = selectedDuration.value,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            color = if (selectedDuration.value == "Длительность") colorScheme.tertiary else colorScheme.onSecondaryContainer,
                        )


                        DropdownMenu(
                            expanded = expandedDuration.value,
                            onDismissRequest = { expandedDuration.value = false },
                            offset = DpOffset(x = 16.dp, y = 0.dp),
                            shape = RoundedCornerShape(10.dp),
                            containerColor = colorScheme.secondaryContainer,
                        ) {
                            durations.forEach { duration ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = duration,
                                            color = colorScheme.onSecondaryContainer,
                                            fontSize = 15.sp,
                                            textAlign = TextAlign.Start,
                                        )
                                    },
                                    onClick = {
                                        selectedDuration.value = duration
                                        expandedDuration.value = false
                                    }
                                )
                            }
                        }
                    }


                }

                Row(
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 28.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f),
                        text = "Баллы учебной практики",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = colorScheme.onPrimary
                    )
                    Checkbox(
                        modifier = Modifier
                            .padding(end = 8.dp),
//                            .border(width = 1.dp,color = Color.Black),
                        checked = selectedPoints.value,
                        onCheckedChange = {
                            selectedPoints.value = !selectedPoints.value
                        },
                        colors = CheckboxColors(

                            checkedCheckmarkColor = colorScheme.onPrimary,
                            uncheckedCheckmarkColor = Color.Transparent,
                            checkedBoxColor = colorScheme.primaryContainer,
                            uncheckedBoxColor = Color.Transparent,
                            disabledCheckedBoxColor = Color.Transparent,
                            disabledUncheckedBoxColor = Color.Transparent,
                            disabledIndeterminateBoxColor = Color.Transparent,
                            checkedBorderColor = colorScheme.onPrimary,
                            uncheckedBorderColor = colorScheme.onPrimary,
                            disabledBorderColor = Color.Transparent,
                            disabledUncheckedBorderColor = Color.Transparent,
                            disabledIndeterminateBorderColor = Color.Transparent

                        ),

                        )
                }


            }
        }

        items(2) { num->
            EventShortScreen(
                navController = navController, event = eventsList[num]
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}