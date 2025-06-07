package com.hse.hseproject.data.datasource.local.student

import com.hse.hseproject.domain.entity.Student
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Inject

class LocalDataSourceStudent @Inject constructor(
    boxStore: BoxStore
) {
    private val studentBox: Box<Student>  = boxStore.boxFor(Student::class.java)

    companion object {
        private const val TAG = "LocalDataSourceStudent"
    }

    fun getFirstStudent(): Student?{
        return studentBox.all.getOrNull(0)
    }

    fun saveStudent(student:Student){

        studentBox.put(student)
    }

    fun getStudent(id:Long):Student{

        return studentBox.get(id)
    }

    fun contains(id:Long):Boolean{

        return studentBox.contains(id)
    }




}