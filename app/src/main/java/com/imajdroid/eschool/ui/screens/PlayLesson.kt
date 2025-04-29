package com.imajdroid.eschool.ui.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.C.VideoScalingMode
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.common.Player.RepeatMode
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.firebase.storage.FirebaseStorage



internal fun Context.findActivity(): Activity{
    var context = this
    while(context is ContextWrapper){
        if(context is Activity) return context
        context = context.baseContext
    }

    throw IllegalStateException("Permissions should be called in the context of an Activity")
}
@Composable
fun PlayLesson(){
//    val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://sollam-8f377.appspot.com/ninth/math/section1/lesson1/mylivewallpapers.com-Ocean-Waves-4K.mp4")
    val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/sollam-8f377.appspot.com/o/ninth%2Fmath%2Fsection1%2Flesson1%2Fmylivewallpapers.com-Ocean-Waves-4K.mp4?alt=media&token=ed9482c7-4d0e-4de2-ba4d-4fa8361705ed")

    val url = "https://firebasestorage.googleapis.com/v0/b/sollam-8f377.appspot.com/o/ninth%2Fmath%2Fsection1%2Flesson1%2Fmylivewallpapers-com-Anonymous-Rain-4K.mp4?alt=media&token=3bef1246-97e0-489a-acb4-73e0dc70166c"


//    val url = "https://drive.google.com/file/d/1QhGSmrKQ_5Y9EAcR6R1HbXQPjFOw6s9U/view?usp=sharing"
    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(context)
        .build()

    val mediaItem = MediaItem.fromUri(Uri.parse(url))
    exoPlayer.setMediaItem(mediaItem)



//    LaunchedEffect(mediaItem ){
//        exoPlayer.setMediaItem(mediaItem)
//        exoPlayer.prepare()
//        Log.i("test", mediaItem.toString())
//    }





    val activity = context.findActivity()

   exoPlayer.seekTo(1, C.TIME_UNSET)
    exoPlayer.repeatMode = REPEAT_MODE_ONE


    val playerView = PlayerView(context)

    playerView.player = exoPlayer


    playerView.setFullscreenButtonClickListener {


        if(
            activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            ||activity.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE

        ){
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }else{
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

    }
    DisposableEffect(

        AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = {

            playerView


        })){

        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        onDispose {
            exoPlayer.release()
//            activity.requestedOrientation = originalOrientation

        }
    }

//    AndroidView(factory = {theContext->
//        PlayerView(theContext).also {
//            it.player = exoPlayer
//        }
//    },
//    modifier = Modifier
//        .fillMaxWidth()
//        .aspectRatio(16 / 9f)
//    )
}