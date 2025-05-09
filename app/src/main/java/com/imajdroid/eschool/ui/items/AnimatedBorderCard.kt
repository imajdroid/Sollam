package com.imajdroid.eschool.ui.items

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedBorderCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 15.dp),
    borderWidth: Dp = 2.dp,
    gradient: Brush = Brush.sweepGradient(listOf(MaterialTheme.colorScheme.primary,Color.White,
        MaterialTheme.colorScheme.primary,Color.White,
        MaterialTheme.colorScheme.primary,Color.White,
        MaterialTheme.colorScheme.primary,Color.White,
        MaterialTheme.colorScheme.primary,Color.White,
        MaterialTheme.colorScheme.primary,Color.White)),
    animationDuration: Int = 30000,
    onCardClick: () -> Unit = {},
    content: @Composable () -> Unit
    ){
//    Color.White, Color(240, 96, 121
    val infiniteTransition = rememberInfiniteTransition(label = "infinite Color Animation")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode =  RepeatMode.Restart
        ),
        label = "Infinite Colors"
    )


    Surface(
        modifier = modifier.clickable {
            onCardClick
                                      },
        shape = shape
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(borderWidth)
                .drawWithContent {
                    rotate(degrees = degrees){
                        drawCircle(brush = gradient,
                            radius = size.width,
                            blendMode = BlendMode.SrcIn)
                    }
                   drawContent()
                },
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        ) {
            content()
        }
    }

}