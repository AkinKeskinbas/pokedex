package com.akin.animesoncompose.videoplayer

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.akin.animesoncompose.R


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun VideoPlayerView(
    episodesColumnVisibility: Boolean,
    isVisible: Boolean,
    visibilityChange: (Boolean) -> Unit,
    backArrowClick: () -> Unit,
    favoriteClick: () -> Unit,
    episodesClick: () -> Unit,
    watchTogetherClick: () -> Unit
) {
    var isFullScreenContent by rememberSaveable { mutableStateOf(false) }
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        VideoPlayer(
            uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            modifier = Modifier,
            fullScreenContent = {
                isFullScreenContent = it
            },
            setVisibilityBackAndEpisodesButton = {
                visibilityChange.invoke(it)
                isFullScreenContent = false
            }
        )
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val list = listOf("", "", "", "", "", "", "", "", "", "", "", "")
            val (userActions, episodesSection) = createRefs()
            AnimatedVisibility(
                visible = episodesColumnVisibility,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .constrainAs(episodesSection) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }) {
                LazyColumn() {
                    itemsIndexed(list) { index, items ->
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Bölüm $index", color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            SpotlightFooter(
                modifier = Modifier.constrainAs(userActions) {
                    end.linkTo(episodesSection.start, 8.dp)
                    top.linkTo(episodesSection.top, 8.dp)
                },
                isVisible,
                isFullScreenContent,
                backArrowClick,
                favoriteClick,
                episodesClick,
                watchTogetherClick
            )
        }

    }
}