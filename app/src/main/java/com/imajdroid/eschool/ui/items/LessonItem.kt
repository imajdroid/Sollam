package com.imajdroid.eschool.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imajdroid.eschool.R
import com.imajdroid.eschool.Vals
import com.imajdroid.eschool.pojo.Lesson

@Composable
fun LessonItem(
    lesson: Lesson,
    onClick:(String)-> Unit) {
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(lesson.lessonId)
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {

            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp),
                painter = painterResource(R.drawable.math),
                contentDescription = null)

//            Icon(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(80.dp)
//                    .height(80.dp),
//                imageVector =Icons.Outlined.PlayCircle,
//                tint = MaterialTheme.colorScheme.primary,
//                contentDescription = null)


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),

                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = lesson.title,
                    fontFamily = Vals.tajwal,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )


            }
        }
    }

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}