package com.hse.hseproject.data.datasource.local.guest

import com.hse.hseproject.domain.entity.Guest
import com.hse.hseproject.domain.entity.Student
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Inject

class LocalDataSourceGuest @Inject constructor(
    boxStore: BoxStore
) {
    private val guestBox: Box<Guest>  = boxStore.boxFor(Guest::class.java)

    companion object {
        private const val TAG = "LocalDataSourceGuest"
    }

    fun getGuest(): Guest?{
        return guestBox.all.getOrNull(0)
    }

    fun saveGuest(guest: Guest){
        guestBox.put(guest)
    }



}