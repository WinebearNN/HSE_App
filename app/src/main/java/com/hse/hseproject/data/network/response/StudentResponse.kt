package com.hse.hseproject.data.network.response

import com.hse.hseproject.domain.entity.StudyYear

data class StudentResponse (
    var globalId:Long,
    var email: String,
    var password: String,
    var phoneNumber: String,
    var studyYear: StudyYear,
    var name: String
)