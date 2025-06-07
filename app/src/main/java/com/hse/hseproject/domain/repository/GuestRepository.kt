package com.hse.hseproject.domain.repository

import com.hse.hseproject.domain.entity.Guest

interface GuestRepository {
    suspend fun logIn(email: String, phoneNumber: String, name: String): Result<Unit>
    suspend fun authentication(): Result<Guest>
}