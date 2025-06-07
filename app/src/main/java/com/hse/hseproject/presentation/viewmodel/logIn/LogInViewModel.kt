package com.hse.hseproject.presentation.viewmodel.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hse.hseproject.domain.useCase.guest.GuestLogInUseCase
import com.hse.hseproject.domain.useCase.student.StudentLogInUseCase
import com.hse.hseproject.util.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val studentLogInUseCase: StudentLogInUseCase,
    private val guestLogInUseCase: GuestLogInUseCase
) : ViewModel() {

    private val _logInResult = MutableStateFlow<LogInResult>(LogInResult.Loading)
    val logInResult: StateFlow<LogInResult> = _logInResult.asStateFlow()


    fun logInGuest(email: String, phoneNumber: String, name: String) {
        viewModelScope.launch {
            _logInResult.value = LogInResult.Loading
            _logInResult.value = guestLogInUseCase.execute(email, phoneNumber, name)
                .fold(
                    onSuccess = {
                        LogInResult.GuestSuccess(true)
                    },
                    onFailure = { error ->
                        when (error) {
                            is Error -> LogInResult.ValidationError(error.errors)
                            else -> LogInResult.Error(error)
                        }
                    }
                )
        }
    }

    // Для входа по email/password
    fun logInStudent(email: String, password: String) {
        viewModelScope.launch {
            _logInResult.value = LogInResult.Loading
            _logInResult.value = studentLogInUseCase.execute(email, password)
                .fold(
                    onSuccess = {
                        LogInResult.StudentSuccess(true)
                    },
                    onFailure = { error ->
                        when (error) {
                            is Error -> LogInResult.ValidationError(error.errors)
                            else -> LogInResult.Error(error)
                        }
                    }
                )
        }
    }
}
