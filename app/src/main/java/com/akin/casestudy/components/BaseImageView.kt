package com.akin.casestudy.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.akin.casestudy.R
import com.akin.casestudy.screens.home.HomeViewModel

@Composable
fun BaseImageView(
    image: String,
    modifier: Modifier,
    viewModel: HomeViewModel,
    getColor: (Color) -> Unit
) {
    var dominantColor by remember { mutableStateOf(Color.Black) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .decoderFactory(GifDecoder.Factory())
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        onSuccess = {
            val drawable = it.result.drawable
            viewModel.calcDominantColor(drawable) { targetColor ->
                dominantColor = targetColor
                getColor.invoke(dominantColor)
            }
        }
    )
}