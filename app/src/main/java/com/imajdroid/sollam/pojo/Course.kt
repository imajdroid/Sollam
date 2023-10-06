package com.imajdroid.sollam.pojo

import java.time.Duration

data class Course(
    val courseId: String,
    val title: String,
    val desc: String,
    val coverUrl: String,
    val price: Double,
    val details: MutableList<HashMap<String, Any>>,
    val duration: HashMap<String, Any>,
    val content: MutableList<HashMap<String, Any>>,

){

    constructor():this("","",
        "","",0.0,
        ArrayList(),
        HashMap(),
        ArrayList()
        )
}