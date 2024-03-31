package com.imajdroid.sollam

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont

class Vals {


    companion object{
        val STATE_NOT_LOADING = 0
        val STATE_LOADING = 1



        val provider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )


        val tajwalName = GoogleFont("Tajawal")

        val tajwal = FontFamily(
            Font(googleFont = tajwalName, fontProvider = provider)
        )
    }

}