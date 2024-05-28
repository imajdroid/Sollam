package com.imajdroid.sollam.ui.screens

import androidx.activity.compose.BackHandler
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
import com.imajdroid.sollam.Vals
import com.imajdroid.sollam.ui.items.AnimatedBorderCard

@Composable
fun CategoriesScreen(onNavigateToCourseDetails: (String) -> Unit, onBackPressed: ()-> Unit){




    val cats = listOf(

        Category("7", "الصف السابع"),
        Category("8", "الصف الثامن"),
        Category("9", "الصف التاسع"),
        Category("10", "الصف الأول ثانوي"),
        Category("11s", "الصف الثاني ثانوي - علمي"),
        Category("11a", "الصف الثاني ثانوي - أدبي"),
        Category("12s", "الصف الثالث ثانوي - علمي"),
        Category("12a", "الصف الثالث ثانوي - أدبي")


    )

    val cells = GridCells.Adaptive(180.dp)

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = cells,
        ) {
            items(cats) { category ->
                AnimatedBorderCard(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(4.dp)
                ) {
                    Card(modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onNavigateToCourseDetails(category.categoryId)
                        }
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = category.categoryName,
                                fontFamily = Vals.tajwal,
                                fontWeight = FontWeight.Bold
                            )
                        }
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