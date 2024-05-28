package com.imajdroid.sollam.pojo

data class Section(
    val sectionId: String = "",
    val title: String = "",
    val thumbnailUrl: String = "",
    val desc: String = "",
    val lessons: MutableList<Lesson> = ArrayList()

) {
}