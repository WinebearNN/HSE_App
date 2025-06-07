package com.hse.hseproject.domain.useCase.student

import android.util.Log
import com.hse.hseproject.domain.entity.Student
import com.hse.hseproject.domain.repository.StudentRepository
import javax.inject.Inject

class StudentAuthUseCase @Inject constructor(private val studentRepository: StudentRepository)  {
    companion object {
        private const val TAG = "StudentAuthUseCase"
    }

    suspend fun auth(): Result<Student> {
        val result=studentRepository.authentication()
        Log.i(TAG,"student: $result")
        return result
    }
}