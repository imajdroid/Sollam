package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imajdroid.eschool.ui.items.AnimatedBorderCard

@Preview
@Composable
fun HomeScreen(){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BasicCard{
                Box(modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                    contentAlignment = Alignment.Center){
                    Text(text = "Awards")
                }
            }
            BasicCard{
                Box(modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                    contentAlignment = Alignment.Center){
                Text(text = "Balance")
                }
            }
        }
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround){
            AnimatedBorderCard(
                modifier = Modifier
                    .width(328.dp)
                    .height(200.dp)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Progress")
                    }
                }
            }
        }
        


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BasicCard{
                Box(modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                    contentAlignment = Alignment.Center){
                    Text(text = "Courses")
                }
            }
            BasicCard{
                Box(modifier = Modifier.fillMaxSize()
                    .background(Color.White)
                    ,
                    contentAlignment = Alignment.Center){
                    Text(text = "Balance")
                }
            }
        }

    }
}


@Composable
fun BasicCard( onClick: ()->Unit ={}, content:  @Composable() (ColumnScope.() -> Unit)){
    AnimatedBorderCard(
        modifier = Modifier
            .width(164.dp)
            .height(164.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick()
                }
        ) {
            content()
        }
    }
}
