buildscript {
    val kotlinVersion ="2.1.10"
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        val navVersion = "2.8.9"



        classpath("com.google.gms:google-services:4.4.2")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
    repositories {
        mavenCentral()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.9.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("com.google.firebase.crashlytics") version "3.0.3" apply false
    kotlin("plugin.serialization") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.10" apply false

}