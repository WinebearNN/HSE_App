package com.hse.hseproject.presentation.ui.ticket

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.hse.hseproject.domain.entity.Format
import com.hse.hseproject.domain.entity.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun TicketEntityScreen(
    navController: NavController,
    ticket:Ticket
) {
    val colorScheme = MaterialTheme.colorScheme

    var openDialog by remember { mutableStateOf(false) }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    if (openDialog) {

        LaunchedEffect(Unit) {
            if (qrBitmap == null && !isLoading && errorMessage == null) {
                isLoading = true
                try {
                    // Generate QR code on background thread
                    qrBitmap = withContext(Dispatchers.Default) {
//                        generateQRCode("EVENT_ID:12345|USER:user@example.com")
                        generateQRCode("TICKET_ID:${ticket.ticketGlobalId}|EVENT_ID:${ticket.eventGlobalId}")
                    }
                } catch (e: Exception) {
                    errorMessage = "Не удалось создать QR-код: ${e.message}"
                } finally {
                    isLoading = false
                }
            }
        }

        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Ваш qr code",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
            },
            text = {
                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    qrBitmap != null -> {
                        Image(
                            bitmap = qrBitmap!!.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                        )
                    }

                    errorMessage != null -> {
                        Text(errorMessage ?: "QR generation failed")
                    }

                    else -> {
                        // Initial state
                    }
                }
            },
            containerColor = Color.White,
            confirmButton = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { openDialog = false },
                ) {
                    Text("${ticket.ticketGlobalId}", fontSize = 18.sp)
                }
            }
        )
    }

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
                .clickable(
                    enabled = true,
                    onClick = {
                        navController.navigate("event/${ticket.eventGlobalId}") {
                            launchSingleTop = true
                        }
                    }
                )
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
                text = ticket.eventCompanyName,
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
                text = ticket.eventName,
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
                text = "${ticket.eventDate},  ${ticket.eventTimeStart}",
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
                    text = if (ticket.eventFormat != Format.ONLINE) ticket.eventAddress else Format.ONLINE.nameFormat,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End,
                    fontSize = 14.sp
                )
            }



        }

//        Spacer(
//            modifier = Modifier
//                .fillMaxHeight()
//                .width(1.dp)
//                .background(
//                    color = colorScheme.tertiary
//                )
//        )

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
                    .fillMaxWidth(),
                onClick = {
                    errorMessage = null
                    qrBitmap = null
                    openDialog = true
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.secondaryContainer
                ),
            ) {
                Text(
                    text = "ID",
                    textAlign = TextAlign.Center,
                    color = colorScheme.onSecondaryContainer
                )
            }

        }

    }

}

private fun generateQRCode(text: String): Bitmap {
    val size = 512  // QR code size
    val qrCodeWriter = QRCodeWriter()
    try {
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size)
        val bitmap = createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap[x, y] =
                    if (bitMatrix[x, y]) Color.Black.hashCode() else Color.White.hashCode()
            }
        }
        return bitmap
    } catch (e: WriterException) {
        throw RuntimeException("QR code generation failed", e)
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun TicketEntityPreview() {
//    TicketEntityScreen(MaterialTheme.colorScheme)
//}