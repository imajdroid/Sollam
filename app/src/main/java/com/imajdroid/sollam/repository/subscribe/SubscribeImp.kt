package com.imajdroid.sollam.repository.subscribe

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore.getInstance
import com.google.firebase.firestore.QuerySnapshot
import com.imajdroid.sollam.pojo.Course
import kotlinx.coroutines.tasks.await

class SubscribeImp {




    suspend fun subscribe(code: String, courseId: String): HashMap<String, Any> {

        var result = HashMap<String, Any>()

        val cardSnap = getInstance()
            .collection("cards")
            .whereEqualTo("code", code)
            .get().await()

        if (cardSnap!!.isEmpty) {
            //Wrong code message
            result["success"] = false
            result["card_passed"] = false
            result["result_code"] = "WRONG_CODE"
            result["message"] = "الرقم الذي أدخلته غير صحيح"

            //Add three times counter here!
        } else {
            result["card_passed"] = true
        }

        if(result["card_passed"] == true as Boolean){
            val course = getTheCourse(courseId)

            course?.let {
                cardSnap?.let {
                    result.putAll(haveTheCourse(course, it, code))
                }
            }
        }


        return result
    }

    private suspend fun getTheCourse(courseId: String): Course? {

        return getInstance()
            .collection("coursesContent")
            .document(courseId)
            .get()
            .await().toObject(Course::class.java)
    }

    private suspend fun haveTheCourse(course: Course, cardSnap: QuerySnapshot, code: String): HashMap<String, Any> {

        val result = HashMap<String, Any>()

        getInstance().runBatch { batch ->
            val studentId = FirebaseAuth.getInstance().currentUser!!.uid

            val ownedCourseRef = getInstance().collection("students")
                .document(studentId)
                .collection("courses")
                .document(course.courseId)


            val cardRef = cardSnap.documents.first()?.reference


            val cardDoc = cardSnap.documents.first()
            val usedCardsRef = getInstance().collection("usedCards")
                .document(cardDoc.id)
            val usedCard = HashMap<String, Any>()
            usedCard["cardId"] = cardDoc.id
            usedCard["code"] = cardDoc.data?.get("code")!!
            usedCard["studentId"] = studentId
            usedCard["createdAt"] = FieldValue.serverTimestamp()


            val purchaseRef = getInstance().collection("purchases")
                .document()

            val purchase = HashMap<String, Any>()
            purchase["purchaseId"] = purchaseRef.id
            purchase["cardId"] = cardDoc.id
            purchase["cardCode"] = code
            purchase["studentId"] = studentId
            purchase["courseId"] = course.courseId
            purchase["amount"] = course.price
            purchase["createdAt"] = FieldValue.serverTimestamp()

            batch.set(ownedCourseRef, course) //Have the course
            batch.set(usedCardsRef, usedCard)//Add the card to the used ones
            batch.set(purchaseRef, purchase)
    //                                batch.delete(cardRef)//Delete the card from the cards collection


        }.await()



        result["success"] = true

        return result
    }
}