package com.imajdroid.sollam.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow



import androidx.compose.ui.unit.sp



@Composable
fun ShowMoreText(text:String = "",   preLines: Int = 2) {


    var showFullSDescription by remember {
        mutableStateOf(false)
    }


    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showFullSDescription = !showFullSDescription
            },
        text = text,
        color = Color.Black.copy(alpha = 0.7f),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Right,
        maxLines = if(showFullSDescription) 100 else preLines,
        overflow = TextOverflow.Ellipsis
    )

}