package com.imajdroid.sollam.repository.city

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.pojo.City
import kotlinx.coroutines.tasks.await

class CityImp {

    suspend fun getCities(): ArrayList<City>{
        val cities = ArrayList<City>()

        FirebaseFirestore.getInstance()
            .collection("cities")
            .get().addOnSuccessListener{

                    for (doc in it.documents){
                        val city = doc.toObject(City::class.java)
                        cities.add(city!!)
                    }
            }.await()
        return cities
    }


}