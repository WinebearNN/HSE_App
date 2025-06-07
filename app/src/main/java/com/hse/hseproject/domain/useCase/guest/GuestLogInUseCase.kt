package com.hse.hseproject.domain.useCase.guest

import com.hse.hseproject.domain.repository.GuestRepository
import com.hse.hseproject.util.CheckValidation
import com.hse.hseproject.util.Error
import com.hse.hseproject.util.ErrorCode
import javax.inject.Inject

class GuestLogInUseCase @Inject constructor(private val guestRepository: GuestRepository) {

    companion object {
        private const val TAG = "GuestLogInUseCase"
    }

    suspend fun execute(email: String, phoneNumber: String, name: String): Result<Unit> {
        val globalErrors = mutableListOf<ErrorCode>()

        validate(email, phoneNumber, globalErrors)

        // Если есть ошибки, возвращаем их
        if (globalErrors.isNotEmpty()) {
            return Result.failure(Error(globalErrors))
        }

        // Вызов метода для входа пользователя
        return guestRepository.logIn(email, phoneNumber, name)
    }



    // Валидация пользователя
    private fun validate(email: String, phoneNumber: String, globalErrors: MutableList<ErrorCode>) {
        // Валидация email
        if (!CheckValidation.Companion.isValidEmail(email)) {
            globalErrors.add(ErrorCode.ERROR_101)
        }

        // Валидация пароля
        if (!CheckValidation.Companion.isValidPhoneNumber(phoneNumber)) {
            globalErrors.add(ErrorCode.ERROR_103)
        }
    }

}