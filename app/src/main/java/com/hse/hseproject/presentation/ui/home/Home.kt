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
import com.hse.hseproject.presentation.ui.event.EventShortScreen

private const val TAG = "HomeScreen"

@Composable
fun HomeScreen(
    navController: NavController,
) {

    val cities = listOf("Нижний Новгород", "Санкт Петербург", "Москва", "Пермь","Любой")
    val formats = listOf("Очный","Онлайн","Любой")
    val durations = listOf("менее 1 часа","менее 1,5 часов","менее 2 часов","Любой")


    var selectedCity = remember { mutableStateOf("Город") }
    var selectedFormat= remember { mutableStateOf("Формат") }
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

                Box{

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
                        color = if (selectedCity.value=="Город") colorScheme.tertiary else colorScheme.onSecondaryContainer,
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
                            color = if (selectedFormat.value=="Формат") colorScheme.tertiary else colorScheme.onSecondaryContainer,
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
                            color = if (selectedDuration.value=="Длительность") colorScheme.tertiary else colorScheme.onSecondaryContainer,
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
                            selectedPoints.value=!selectedPoints.value
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

        items(8) {
            EventShortScreen(navController = navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}