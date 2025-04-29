package com.imajdroid.eschool.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imajdroid.eschool.ui.items.ShimmerListItem


@Composable
fun ShimmerEffectScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(20){
            ShimmerListItem(isLoading = true, contentAfterLoading = { })
        }
    }
}