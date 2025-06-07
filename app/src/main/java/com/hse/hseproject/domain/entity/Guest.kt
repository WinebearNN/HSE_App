package com.hse.hseproject.domain.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Guest (
    @Id var id:Long = 0,
    var email: String,
    var phoneNumber: String,
    var name: String
)