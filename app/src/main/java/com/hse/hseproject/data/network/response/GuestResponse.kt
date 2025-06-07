package com.hse.hseproject.data.network.response

import io.objectbox.annotation.Id

data class GuestResponse (
    var globalId:Long,
    var email: String,
    var phoneNumber: String,
    var name: String
)