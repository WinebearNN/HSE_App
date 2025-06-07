package com.hse.hseproject.presentation.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hse.hseproject.R

private const val TAG = "ProfileScreen"

@Composable
fun ProfileScreen(
    navController: NavController,
) {

    // Получаем текущую цветовую схему
    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Column {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Профиль",
                        fontStyle = FontStyle.Normal,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxSize()
                            .background(
                                color = colorScheme.primaryContainer,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = 8.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 0.dp, start = 16.dp),
                        ) {
                            Text(
                                text = "Зимин Владимир Владимирович",
                                color = colorScheme.outlineVariant,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis

                                )
                            Text(
                                text = "Бакалавриат 23 КНТ-1",
                                color = colorScheme.tertiary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 0.dp, end = 16.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.no_img_person),
                                contentDescription = "Button Icon",
                                modifier = Modifier.size(72.dp)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    )
                    .fillMaxWidth()
                    .height(420.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)),
                ) {
                    Text(
                        text = "Настройки",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        fontSize = TextUnit(18f, TextUnitType.Sp)
                    )
                    Chapter(
                        modifier = Modifier
                            .padding(top = 12.dp),
                        text = "Уведомления",
                        icon = ImageVector.vectorResource(R.drawable.notification)
                    )
                    Chapter(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = "Экспорт в календарь",
                        icon = ImageVector.vectorResource(R.drawable.calendar)
                    )
                    Chapter(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = "Язык",
                        icon = ImageVector.vectorResource(R.drawable.language)
                    )
                    Chapter(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = "Помощь",
                        icon = ImageVector.vectorResource(R.drawable.help)
                    )
                    Chapter(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = "О приложении",
                        icon = ImageVector.vectorResource(R.drawable.info)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top=28.dp)
                            .fillMaxSize()
                            .clickable(enabled = true, onClick = {})
                            .wrapContentSize(),
                        text = "Выйти",
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

}

@Composable
fun Chapter(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(
                enabled = true,
                onClick = {}
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Уведомления",
            modifier = modifier
                .padding(start = 16.dp)
                .clickable(true, onClick = {})
                .size(48.dp)
        )
        Text(
            text = text,
            modifier = modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .height(24.dp),
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}
