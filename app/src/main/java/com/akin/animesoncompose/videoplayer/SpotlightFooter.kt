package com.akin.animesoncompose.videoplayer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Spotlight footer
 *
 * @param spotlight
 */
@Composable
fun SpotlightFooter(
    modifier: Modifier,
    isVisible: Boolean,
    isFullScreen: Boolean,
    backArrowClick: () -> Unit,
    favoriteClick: () -> Unit,
    episodesClick: () -> Unit,
    watchTogetherClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 18.dp, bottom = 18.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedVisibility(visible = isVisible) {
            FooterUserAction(
                modifier = Modifier.fillMaxWidth(),
                isFullScreen,
                backArrowClick,
                favoriteClick,
                episodesClick,
                watchTogetherClick
            )
        }


//        FooterUserAction(
//            modifier = Modifier.weight(2f)
//        )
    }
}