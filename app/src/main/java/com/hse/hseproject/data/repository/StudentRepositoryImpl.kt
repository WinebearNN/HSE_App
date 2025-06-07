package com.hse.hseproject.data.repository

import com.google.gson.Gson
import com.hse.hseproject.data.datasource.local.student.LocalDataSourceStudent
import com.hse.hseproject.data.datasource.remote.student.RemoteDataSourceStudent
import com.hse.hseproject.data.network.response.StudentResponse
import com.hse.hseproject.domain.entity.Student
import com.hse.hseproject.domain.repository.StudentRepository
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
    private val remoteDataSourceStudent: RemoteDataSourceStudent,
    private val localDataSourceStudent: LocalDataSourceStudent
) : StudentRepository {

    companion object {
        private const val TAG = "StudentRepositoryImpl"
    }

    override suspend fun logIn(email: String, password: String): Result<Unit> {
        val result = remoteDataSourceStudent.logInStudent(email, password)
        if (result.isSuccess) {
            val studentResponse = Gson().fromJson<StudentResponse>(
                result.getOrNull()!!,
                StudentResponse::class.java
            )
            val student = Student(
                globalId = studentResponse.globalId,
                email = studentResponse.email,
                password = studentResponse.password,
                studyYear = studentResponse.studyYear,
                phoneNumber = studentResponse.phoneNumber,
                name = studentResponse.name
            )
            localDataSourceStudent.saveStudent(student)
            return Result.success(Unit)
        }
        return Result.failure(Exception(result.getOrNull()))
    }

    override suspend fun authentication(): Result<Student> {
        return localDataSourceStudent.getFirstStudent()?.let {
            Result.success(it)
        }
            ?: Result.failure(Exception("Student not auth before"))
    }

}