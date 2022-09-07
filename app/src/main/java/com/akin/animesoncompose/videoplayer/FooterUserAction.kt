package com.akin.animesoncompose.videoplayer

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Footer user action
 *
 * @param modifier
 */
@Composable
fun FooterUserAction(
    modifier: Modifier,
    isFullScreen: Boolean,
    backArrowClick: () -> Unit,
    favoriteClick: () -> Unit,
    episodesClick: () -> Unit,
    watchTogetherClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        if (isFullScreen) {
            UserAction(icon = Icons.Default.ArrowBack, isFullScreen) {
                backArrowClick.invoke()
            }
        }else{
            Spacer(modifier = Modifier.size(25.dp))
        }
        Column() {
            UserAction(
                icon = Icons.Outlined.Favorite,
                isFullScreen

            ) {
                favoriteClick.invoke()
            }
            Spacer(modifier = Modifier.height(10.dp))
            UserAction(
                icon = Icons.Default.DynamicFeed,
                isFullScreen
            ) {
                episodesClick.invoke()
            }
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(10.dp))
                UserAction(
                    icon = Icons.Default.ScreenShare,
                    isFullScreen
                ) {
                    watchTogetherClick.invoke()
                }
            }

        }
    }
}