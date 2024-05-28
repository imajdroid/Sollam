package com.imajdroid.sollam.pojo

data class Course(
    val courseId: String ="",
    val title: String ="",
    val desc: String = "",

    val coverUrl: String = "",
    val price: Double = 0.0,
    val details: MutableList<HashMap<String, Any>> = ArrayList<HashMap<String, Any>>(),
    val content: MutableList<HashMap<String, Any>> = ArrayList<HashMap<String, Any>>(),


    val sections: MutableList<Section> = ArrayList(),

    val duration: HashMap<String, Any> = HashMap(),
    val instructor: HashMap<String, Any> = HashMap()
){

}