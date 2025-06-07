package com.hse.hseproject.presentation.ui.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicketEntityScreen(
    colorScheme: ColorScheme
) {


    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(156.dp)
            .fillMaxWidth()
            .background(
                color = colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                vertical = 6.dp,
                horizontal = 12.dp
            )

    ) {

        Column(
            modifier = Modifier
                .weight(2.5f)
        ) {


            Text(
                modifier = Modifier
                    .padding(
                        vertical = 1.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(
                        align = Alignment.Top
                    ),
                text = "Yandex",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .padding(
                        vertical = 1.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(
                        align = Alignment.Top
                    ),
                text = "День открытых дверей.",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .padding(
                        vertical = 1.dp
                    ),
                text = "26 Июня 2025,  17:00",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End,
                fontSize = 14.sp,
                letterSpacing = 0.5.sp
            )
//            Text(
//                modifier = Modifier
//                    .padding(
//                        bottom = 1.dp
//                    ),
//                text = "17:00 - 18:30",
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                textAlign = TextAlign.End,
//                fontSize = 14.sp
//            )

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom

            ) {


                Text(
                    modifier = Modifier
                        .padding(
                            vertical = 1.dp,
                        ),
                    text = "ул. Родионова 186",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    fontSize = 14.sp
                )
            }


        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(
                    color = colorScheme.tertiary
                )
        )

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Button(
                modifier = Modifier
                    .padding(
                        start = 14.dp,
                        end = 2.dp,
                        top = 8.dp,
                        bottom = 6.dp
                    )
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = {},
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.secondaryContainer
                ),
            ) {
                Text(
//                    modifier= Modifier
//                        .align(Alignment.CenterHorizontally),
                    text = "ID",
                    textAlign = TextAlign.Center,
                    color = colorScheme.onSecondaryContainer
                )
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
private fun TicketEntityPreview() {
    TicketEntityScreen(MaterialTheme.colorScheme)
}