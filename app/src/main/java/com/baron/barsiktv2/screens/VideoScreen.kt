package com.baron.barsiktv2.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.baron.barsiktv2.MainViewModel
import com.baron.data.network.BarsikRetrofit

@OptIn(UnstableApi::class)
@Composable
fun VideoScreen(
    viewModel: MainViewModel,
    navController: NavController,
){

    val context = LocalContext.current
    val torrentFile by viewModel.torrentFile.collectAsState()

    val screen = context.resources.displayMetrics
    val trackSelector = DefaultTrackSelector(context).apply {
        setParameters(buildUponParameters().setMaxVideoSize(screen.widthPixels, screen.heightPixels))
    }
    val exoPlayer = ExoPlayer.Builder(context).setTrackSelector(trackSelector).build()

    val hash = torrentFile.first
    val index = torrentFile.second
    val link = "${BarsikRetrofit.BASE_URL}torrent/file?info_hash=$hash&index=$index"

    val mediaSource = remember(link) {
        MediaItem.fromUri(link)
    }
    Box(
        modifier = Modifier.background(Color.Black).fillMaxSize()
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}