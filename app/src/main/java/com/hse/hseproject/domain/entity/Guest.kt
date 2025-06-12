package com.hse.hseproject.domain.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Guest (
    @Id var id:Long = 0,
    val email: String,
    val phoneNumber: String,
    val name: String
)