package com.hse.hseproject.domain.entity


enum class EventDuration(
    private val duration: String
) {

    ONE_HOUR("Менее 1 часа"),
    ONE_HALF_HOUR("Менее 1,5 часов"),
    TWO_HOURS("Менее 2 часов"),
    ANY("Любой");

    val durationEvent: String
        get() = duration

}

