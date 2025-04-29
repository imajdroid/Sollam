package com.imajdroid.eschool.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imajdroid.eschool.R
import com.imajdroid.eschool.Vals


@Composable
fun TitleText(text: String, paddingValues: PaddingValues = PaddingValues(16.dp)){




    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(paddingValues),
        text = text,
        textAlign = TextAlign.Unspecified,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = Vals.tajwal
    )
}