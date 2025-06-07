package com.hse.hseproject.domain.useCase.guest

import android.util.Log
import com.hse.hseproject.domain.entity.Guest
import com.hse.hseproject.domain.repository.GuestRepository
import javax.inject.Inject

class GuestAuthUseCase @Inject constructor(private val guestRepository: GuestRepository) {

    companion object {
        private const val TAG = "GuestAuthUseCase"
    }

    suspend fun auth(): Result<Guest> {
        val result = guestRepository.authentication()
        Log.i(TAG, "guest: $result")
        return result
    }
}