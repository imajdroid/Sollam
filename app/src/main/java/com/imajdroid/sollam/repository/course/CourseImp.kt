package com.imajdroid.sollam.repository.course

import com.google.firebase.firestore.FirebaseFirestore
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.pojo.Course
import kotlinx.coroutines.tasks.await

class CourseImp {


    suspend fun getCourseById(courseId: String): Course {

        return FirebaseFirestore.getInstance()
            .collection("courses")
            .document(courseId)
            .get().await().toObject(Course::class.java)!!

    }

    suspend fun getOwnedCourseById(courseId: String): Course {

        return FirebaseFirestore.getInstance()
            .collection("students")
            .document(Vals.student!!.studentId)
            .collection("courses")
            .document(courseId)
            .get().await().toObject(Course::class.java)!!

    }

    suspend fun isCourseOwned(courseId: String): Boolean {

        return FirebaseFirestore.getInstance()
            .collection("students")
            .document(Vals.student!!.studentId)
            .collection("courses")
            .document(courseId)
            .get().await().exists()

    }

    suspend fun getMyCourses(): ArrayList<Course>{

        val studentId = Vals.student!!.studentId

        val snapshot = FirebaseFirestore.getInstance()
            .collection("students")
            .document(studentId)
            .collection("courses")
            .get().await()


        val courses = ArrayList<Course>()

        snapshot.documents.forEach{doc->
            val course = doc.toObject(Course::class.java)
            courses.add(course!!)
        }
        return courses
    }




    suspend fun getCourses(): ArrayList<Course> {
        val snapshot = FirebaseFirestore.getInstance()
           .collection("courses")
           .get().await()


        val courses = ArrayList<Course>()

        snapshot.documents.forEach{
            courses.add(it.toObject(Course::class.java)!!)
        }
        return courses
    }

    suspend fun getCoursesByCategory(categoryId: String): ArrayList<Course> {

        val snapshot = FirebaseFirestore.getInstance()
            .collection("courses")
            .whereEqualTo("categoryId", categoryId)
            .get().await()



        val courses = ArrayList<Course>()

        snapshot.documents.forEach{
            courses.add(it.toObject(Course::class.java)!!)
        }


        return courses

    }


}