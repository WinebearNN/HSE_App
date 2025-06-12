package com.hse.hseproject.domain.entity

import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.converter.PropertyConverter


@Entity
data class Student (
    @Id var id:Long = 0,
    val globalId:Long,
    val email: String,
    val password: String,
    val phoneNumber: String,

    @Convert(converter = StudyYearConverter::class, dbType = Int::class)
    val studyYear: StudyYear,

    val name: String
)


class StudyYearConverter : PropertyConverter<StudyYear, Int> {
    override fun convertToEntityProperty(databaseValue: Int?): StudyYear? {
        return StudyYear.fromValue(databaseValue ?: 0) // 0 = DEFAULT
    }

    override fun convertToDatabaseValue(entityProperty: StudyYear?): Int {
        return entityProperty?.value ?: 0 // 0 = DEFAULT
    }
}