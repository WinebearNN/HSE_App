package com.hse.hseproject.domain.useCase.student

import com.hse.hseproject.domain.repository.StudentRepository
import com.hse.hseproject.util.CheckValidation
import com.hse.hseproject.util.Error
import com.hse.hseproject.util.ErrorCode
import javax.inject.Inject

class StudentLogInUseCase @Inject constructor(private val studentRepository: StudentRepository) {

    companion object {
        private const val TAG = "StudentLogInUseCase"
    }

    suspend fun execute(email:String,password:String): Result<Unit> {
        val globalErrors = mutableListOf<ErrorCode>()

        validateUser(email,password, globalErrors)

        // Если есть ошибки, возвращаем их
        if (globalErrors.isNotEmpty()) {
            return Result.failure(Error(globalErrors))
        }

        // Вызов метода для входа пользователя
        return studentRepository.logIn(email,password)
    }


    // Валидация пользователя
    private fun validateUser(email:String,password:String, globalErrors: MutableList<ErrorCode>) {
        // Валидация email
        if (!CheckValidation.Companion.isValidEmail(email)) {
            globalErrors.add(ErrorCode.ERROR_101)
        }

        // Валидация пароля
        if (!CheckValidation.Companion.isValidPassword(password)) {
            globalErrors.add(ErrorCode.ERROR_102)
        }
    }

}