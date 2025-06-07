package com.hse.hseproject.presentation.ui.logIn

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hse.hseproject.R
import com.hse.hseproject.presentation.viewmodel.logIn.LogInResult
import com.hse.hseproject.presentation.viewmodel.logIn.LogInViewModel
import kotlinx.coroutines.launch


private const val TAG = "LogInScreen"

@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: LogInViewModel = hiltViewModel()
) {


    val logInResult by viewModel.logInResult.collectAsState()
    val scope = rememberCoroutineScope()



    // 2. Обрабатываем результат
    LaunchedEffect(logInResult) {
        when (logInResult) {


            is LogInResult.Error -> {
                Log.e(TAG, "LogIn failed", (logInResult as LogInResult.Error).exception)
            }

            LogInResult.Loading -> {
                // Показываем загрузку (опционально)
            }

            is LogInResult.ValidationError -> {
                // Показываем ошибки валидации (если используете login-форму)
                val errors = (logInResult as LogInResult.ValidationError).errorCodes
                errors.forEach { errorCode ->
                    Log.w(TAG, "Validation error: $errorCode")
                }
            }

            is LogInResult.GuestSuccess -> {
                navController.navigate(route = "profile") {
                    popUpTo("authentication")
                    launchSingleTop = true
                }
            }
            is LogInResult.StudentSuccess -> {
                navController.navigate(route = "profile") {
                    popUpTo("authentication")
                    launchSingleTop = true
                }
            }
        }
    }

    // Получаем текущую цветовую схему
    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()

    var isStudent by remember { mutableStateOf(true) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Логотип - можно добавить разные варианты для темной и светлой темы
            Image(
                painter = painterResource(id = if (isDarkTheme) R.drawable.hse_logo_dark else R.drawable.hse_logo),
                contentDescription = "Логотип ВШЭ",
                modifier = Modifier.size(96.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Переключатель студент/гость
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TabButton(
                    text = "Студент",
                    isSelected = isStudent,
                    onClick = { isStudent = true },
                    isDarkTheme = isDarkTheme
                )
                Spacer(modifier = Modifier.width(16.dp))
                TabButton(
                    text = "Гость",
                    isSelected = !isStudent,
                    onClick = { isStudent = false },
                    isDarkTheme = isDarkTheme
                )
            }

            if (isStudent) {
                // Форма для студентов
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,

                        focusedLabelColor = colorScheme.onPrimary,
                        unfocusedLabelColor = colorScheme.tertiary,

                        focusedBorderColor = colorScheme.onPrimary,
                        unfocusedBorderColor = colorScheme.onPrimary,

                        cursorColor = colorScheme.onPrimary
                    ),
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Email),
                            contentDescription = "Email icon",
                            tint = colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Пароль") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,

                        focusedLabelColor = colorScheme.onPrimary,
                        unfocusedLabelColor = colorScheme.tertiary,

                        focusedBorderColor = colorScheme.onPrimary,
                        unfocusedBorderColor = colorScheme.onPrimary,

                        cursorColor = colorScheme.onPrimary
                    ),
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Lock),
                            contentDescription = "Password icon",
                            tint = colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            viewModel.logInStudent(email, password)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.secondary,
                        contentColor = colorScheme.onSecondary
                    )
                ) {
                    Text("Войти")
                }
            } else {
                // Форма для гостей
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Ваше имя") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(


                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,

                        focusedLabelColor = colorScheme.onPrimary,
                        unfocusedLabelColor = colorScheme.tertiary,

                        focusedBorderColor = colorScheme.onPrimary,
                        unfocusedBorderColor = colorScheme.onPrimary,

                        cursorColor = colorScheme.onPrimary
                    ),
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Person),
                            contentDescription = "Person icon",
                            tint = colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Номер телефона") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = OutlinedTextFieldDefaults.colors(


                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,

                        focusedLabelColor = colorScheme.onPrimary,
                        unfocusedLabelColor = colorScheme.tertiary,

                        focusedBorderColor = colorScheme.onPrimary,
                        unfocusedBorderColor = colorScheme.onPrimary,

                        cursorColor = colorScheme.onPrimary
                    ),
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Phone),
                            contentDescription = "Phone icon",
                            tint = colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(

                        focusedContainerColor = colorScheme.surface,
                        unfocusedContainerColor = colorScheme.surface,

                        focusedLabelColor = colorScheme.onPrimary,
                        unfocusedLabelColor = colorScheme.tertiary,

                        focusedBorderColor = colorScheme.onPrimary,
                        unfocusedBorderColor = colorScheme.onPrimary,

                        cursorColor = colorScheme.onPrimary
                    ),
                    leadingIcon = {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.Email),
                            contentDescription = "Email icon",
                            tint = colorScheme.onSurfaceVariant
                        )
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /*onGuestLogin(name, phone, email)*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.secondary,
                        contentColor = colorScheme.onSecondary
                    )
                ) {
                    Text("Войти как гость")
                }
            }
        }
    }
}

@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    isDarkTheme: Boolean
) {
    val backgroundColor = if (isSelected) {
        if (isDarkTheme) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondary
    } else {
        if (isDarkTheme) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.primary
    }

    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onSecondary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 4.dp else 0.dp
        ),
        border =


            if (!isSelected) {
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            } else {
                null
            }

    ) {
        Text(text)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLogInScreen() {
    LogInScreen(navController = rememberNavController())
}

