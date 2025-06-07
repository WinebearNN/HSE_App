package com.hse.hseproject.presentation.viewmodel.logIn

import com.hse.hseproject.domain.entity.Guest
import com.hse.hseproject.domain.entity.Student
import com.hse.hseproject.util.ErrorCode

sealed class LogInResult {
    data class StudentSuccess(val success: Boolean) : LogInResult()
    data class GuestSuccess(val success: Boolean) : LogInResult()
    data class Error(val exception: Throwable) : LogInResult()
    data class ValidationError(val errorCodes: List<ErrorCode>) : LogInResult()
    object Loading : LogInResult()
}