package com.imajdroid.eschool.viewmodels

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle


): ViewModel() {


    private val videoUrls = savedStateHandle.getStateFlow("videoUrls", emptyList<String>())

    val videoItems = videoUrls.map { url->

    }

}