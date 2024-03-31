package com.imajdroid.sollam.repository.school

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.pojo.School
import kotlinx.coroutines.tasks.await

class SchoolImp {



    suspend fun getCategorizedSchoolsOfCity(cityId: String, isPrivate: Boolean): ArrayList<School>{

        val schools = ArrayList<School>()

        Log.i("SCHOOL", "Running")
        Log.i("city", cityId)
        Log.i("isPrivate", isPrivate.toString())

        FirebaseFirestore.getInstance()
            .collection("schools")
            .whereEqualTo("cityId", cityId)
            .whereEqualTo("isPrivate", isPrivate)
            .get()
            .addOnSuccessListener {

                for (doc in it.documents)
                    schools.add(doc.toObject(School::class.java)!!)


            }.await()

        return schools
    }


}