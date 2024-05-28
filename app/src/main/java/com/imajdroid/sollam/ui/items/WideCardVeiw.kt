package com.imajdroid.sollam.ui.items

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.ui.components.DescriptionText
import org.jetbrains.annotations.Async


@Composable
fun WideCardView(
    modifier: Modifier = Modifier,
    imageUrl : String,
    title: String,
    desc: String,
    onClick : () -> Unit = {}
){


    var showFullSDescription by remember {
        mutableStateOf(false)
    }




    Card(
        modifier = modifier
            .animateContentSize()
            .padding(8.dp)
            .clickable {
                onClick()
            },

        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer),
        shape = MaterialTheme.shapes.medium,

        colors = CardDefaults.cardColors(containerColor = Color.White),

        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp,  focusedElevation = 0.dp)


    ) {


        Column {


            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right,
                    fontFamily = Vals.tajwal

                )
                
                Spacer(modifier = Modifier.height(8.dp))

               Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                   text = desc,
                   textAlign = TextAlign.Right,
                   fontFamily = Vals.tajwal
               )

            }
        }
    }

}

