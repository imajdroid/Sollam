package com.imajdroid.sollam.repository.course

import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.pojo.Course
import kotlinx.coroutines.tasks.await

class CourseImp {



    suspend fun getCourseById(courseId: String) : Course{
        var course: Course = Course()

        FirebaseFirestore.getInstance()
            .collection("courses")
            .document(courseId)
            .get().addOnSuccessListener {
                course = it.toObject(Course::class.java)!!

            }.await()

        return course
    }


    suspend fun getCourses(): ArrayList<Course> {
        val courses = ArrayList<Course>()


        FirebaseFirestore.getInstance()
           .collection("courses")
           .get().addOnSuccessListener {
               for (doc in it.documents){
                   val course = doc.toObject(Course::class.java)!!
                   courses.add(course)
               }

           }.await()
        return courses
    }

    suspend fun getCoursesByCategory(categoryId: String): ArrayList<Course> {
        val courses = ArrayList<Course>()


        FirebaseFirestore.getInstance()
            .collection("courses")
            .whereEqualTo("categoryId", categoryId)
            .get().addOnSuccessListener {
                for (doc in it.documents){
                    val course = doc.toObject(Course::class.java)!!
                    courses.add(course)
                }

            }.await()
        return courses

    }


}