package com.akin.casestudy.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BaseAsyncImage(image:String, modifier: Modifier, placeholder:Int=0) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder =if (placeholder!=0) painterResource(placeholder) else null,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}