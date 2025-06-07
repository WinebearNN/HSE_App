package com.hse.hseproject.domain.entity

enum class StudyYear (val value: Int, val translation: String) {
    DEFAULT(0, "Абитуриент"),
    FIRST_YEAR(1, "Первый курс"),
    SECOND_YEAR(2, "Второй курс"),
    THIRD_YEAR(3,"Третий курс"),
    FOURTH_YEAR(4,"Четвертый курс"),
    FIFTH_YEAR(5,"Пятый курс"),
    FIRST_MASTER(6,"Первый курс магистратуры"),
    SECOND_MASTER(7,"Второй курс магистратуры");


    companion object {
        fun fromValue(value: Int): StudyYear? {
            return entries.find { it.value == value }
        }

        fun fromTranslation(translation: String): StudyYear? {
            return entries.find { it.translation == translation }
        }
    }
}