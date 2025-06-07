package com.hse.hseproject.data.network.request

import com.hse.hseproject.domain.entity.StudyYear
import io.objectbox.annotation.Id

data class StudentRequest (
    var email: String = "",
    var password: String = "",
)