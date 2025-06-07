package com.hse.hseproject.presentation.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hse.hseproject.domain.useCase.guest.GuestAuthUseCase
import com.hse.hseproject.domain.useCase.student.StudentAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val studentAuthUseCase: StudentAuthUseCase,
    private val guestAuthUseCase: GuestAuthUseCase
) : ViewModel() {

    private val _authResult = MutableStateFlow<AuthResult>(AuthResult.Loading)
    val authResult: StateFlow<AuthResult> = _authResult.asStateFlow()



    // Для автоматической авторизации (например, при открытии приложения)
    fun auth() {
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading
            studentAuthUseCase.auth()
                .fold(
                    onSuccess = { student ->
                        _authResult.value = AuthResult.StudentSuccess(student)
                    },
                    onFailure = { studentError ->
                        // Если студент не авторизован, пробуем гостя
                        guestAuthUseCase.auth()
                            .fold(
                                onSuccess = { guest ->
                                    _authResult.value = AuthResult.GuestSuccess(guest)
                                },
                                onFailure = { guestError ->
                                    _authResult.value = AuthResult.Error(guestError)
                                }
                            )
                    }
                )
        }
    }
}