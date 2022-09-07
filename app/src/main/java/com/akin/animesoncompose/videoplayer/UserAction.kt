package com.akin.animesoncompose.videoplayer

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * User action
 *
 * @param icon
 */
@Composable
fun UserAction(
    icon: ImageVector,
    isFullScreen:Boolean,
    clickAction: ()->Unit
) {
    Icon(
        imageVector = icon,
        tint = Color.White,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 10.dp)
            .clip(CircleShape)
            .size(if(isFullScreen) 25.dp else 20.dp)
            .clickable { clickAction.invoke() },
        contentDescription = null
    )
}