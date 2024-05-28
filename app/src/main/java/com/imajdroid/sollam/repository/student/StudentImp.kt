package com.imajdroid.sollam.repository.student

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Student
import kotlinx.coroutines.tasks.await

class StudentImp {



    suspend fun getStudentData() : Student{
        var result = Student()

        FirebaseFirestore.getInstance()
            .collection("students")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                if(it.exists()) {
                    result = it.toObject(Student::class.java)!!
                    Vals.student = result
                }
            }.await()

        return result
    }

    suspend fun addNewStudentData(student: Student) : Boolean{

        var addedSuccessfully = false
        FirebaseFirestore.getInstance().collection("students")
            .document(student.studentId).set(student).await()

        Vals.student = student

        addedSuccessfully = updateProfile(student)

        return addedSuccessfully
    }

    private suspend fun updateProfile(student: Student): Boolean{

        val changeRequest = UserProfileChangeRequest.Builder()

        changeRequest.displayName = student.firstName + " " + student.surname

        FirebaseAuth.getInstance()
            .currentUser?.updateProfile(changeRequest.build())?.await()
        return true
    }

}