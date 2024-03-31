package com.imajdroid.sollam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imajdroid.sollam.ui.items.AnimatedBorderCard

@Composable
fun CategoriesScreen(onNavigateToCourseDetails: (String) -> Unit){

    val cats = listOf(
        Category("one", "الصف الأول"),
        Category("two", "الصف الثاني"),
        Category("three", "الصف الثالث"),
        Category("four", "الصف الرابع"),
        Category("five", "الصف الخامس"),
        Category("six", "الصف السادس"),
        Category("seven", "الصف السابع"),
        Category("eight", "الصف الثامن"),
        Category("nine", "الصف التاسع"),
        Category("ten", "الصف الأول ثانوي"),
        Category("eleven", "الصف الثاني ثانوي"),
        Category("twelve", "الصف الثالث ثانوي")
    )

    val cells = GridCells.Adaptive(180.dp)

    LazyVerticalGrid(columns = cells,
    ){
        items(cats){category->
            AnimatedBorderCard(
                modifier= Modifier
                    .height(120.dp)
                    .padding(4.dp)
                ) {
                    Card(modifier= Modifier
                        .fillMaxSize()
                        .clickable {
                            onNavigateToCourseDetails(category.categoryId)
                        }
                        ) {

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                            contentAlignment = Alignment.Center

                        ){
                            Text(text = category.categoryName,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
            }
        }
     }
}

data class Category(
    val categoryId: String,
    val categoryName: String
)