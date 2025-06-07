package com.hse.hseproject.presentation.viewmodel.auth

import com.hse.hseproject.domain.entity.Guest
import com.hse.hseproject.domain.entity.Student

sealed class AuthResult {
    data class StudentSuccess(val student: Student) : AuthResult()
    data class GuestSuccess(val guest: Guest) : AuthResult()
    data class Error(val exception: Throwable) : AuthResult()
    object Loading : AuthResult()
}