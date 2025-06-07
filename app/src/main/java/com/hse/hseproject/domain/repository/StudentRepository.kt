package com.hse.hseproject.domain.repository

import com.hse.hseproject.domain.entity.Student

interface StudentRepository {
    suspend fun logIn(email:String,password:String): Result<Unit>
    suspend fun authentication(): Result<Student>
}