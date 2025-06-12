package com.hse.hseproject.domain.entity

enum class Format(
    private val format: String,
) {
    ONLINE("Онлайн"),
    IN_PERSON("Очно"),
    ANY("Любой");

    val nameFormat: String
        get() = format


}
