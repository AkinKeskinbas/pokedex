package com.akin.animesoncompose.videoplayer

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayer(
    uri: Uri,
    modifier: Modifier,
    setVisibilityBackAndEpisodesButton: (Boolean) -> Unit,
    fullScreenContent: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var playWhenReady by rememberSaveable { mutableStateOf(true) }
    var currentPosition by rememberSaveable { mutableStateOf(0L) }
    var isFullScreenContent by rememberSaveable { mutableStateOf(false) }


    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(uri))

                setMediaSource(source)
                prepare()
            }
    }
    LaunchedEffect(key1 = currentPosition) {
        exoPlayer.seekTo(currentPosition)
    }
    exoPlayer.playWhenReady = playWhenReady
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE


    DisposableEffect(

        AndroidView(modifier = modifier, factory = {

            PlayerView(context).apply {
                setControllerVisibilityListener(PlayerView.ControllerVisibilityListener {
                    if (it == 0) {
                        setVisibilityBackAndEpisodesButton.invoke(true)
                    } else {
                        setVisibilityBackAndEpisodesButton.invoke(false)
                    }
                })

                //setOnClickListener {visibilityChange.invoke()}
                useController = true
                useArtwork = true

                player = exoPlayer
                PlayerControlView(context).setProgressUpdateListener { position, bufferedPosition ->
                    println("current: $position")
                }


                setFullscreenButtonClickListener { isFullScreen ->
                    currentPosition = (player as ExoPlayer).contentPosition
                    playWhenReady = exoPlayer.playWhenReady
                    isFullScreenContent = isFullScreen
                    with(context) {
                        if (isFullScreen) {
                            fullScreenContent.invoke(true)
                            setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                        } else {
                            fullScreenContent.invoke(false)
                            setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

                        }
                    }

                }
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH


                layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            }
        })
    ) {
        onDispose { exoPlayer.release() }

    }
    ComposableLifecycle { source, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            exoPlayer.pause()
        } else if (event == Lifecycle.Event.ON_RESUME) {
            exoPlayer.play()
        }
    }
}