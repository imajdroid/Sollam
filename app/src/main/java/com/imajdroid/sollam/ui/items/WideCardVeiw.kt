package com.imajdroid.sollam.ui.items

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.imajdroid.sollam.ui.components.DescriptionText


@Composable
fun WideCardView(
    modifier: Modifier = Modifier,
    @DrawableRes image : Int,
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
            .clickable {
                onClick()
            },
        shape = MaterialTheme.shapes.medium,

        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {


        Column {


            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Right,

                )
                
                Spacer(modifier = Modifier.height(10.dp))

                DescriptionText(text = desc)

            }
        }
    }

}

