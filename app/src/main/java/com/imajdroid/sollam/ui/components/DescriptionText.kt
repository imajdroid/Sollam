package com.imajdroid.sollam.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imajdroid.sollam.Vals


@Composable
fun DescriptionText(text: String, paddingValues: PaddingValues = PaddingValues(16.dp)) {


    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(paddingValues),
        text = text,
        textAlign = TextAlign.Right,
        fontFamily = Vals.tajwal

    )


}