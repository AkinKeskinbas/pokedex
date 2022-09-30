package com.akin.casestudy.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akin.casestudy.components.BaseImageView
import com.akin.casestudy.data.model.PokemonViewItem

@Composable
fun PokemonCard(pokemonList: PokemonViewItem, modifier: Modifier, viewModel: HomeViewModel) {
    var bgColor by remember { mutableStateOf(Color.Transparent) }

    Card(
        modifier = Modifier.size((LocalConfiguration.current.screenWidthDp / 1.5).dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            BaseImageView(
                image = pokemonList.image,
                modifier = modifier
                    .size(300.dp)
                    .align(Alignment.Center),
                viewModel = viewModel
            ) {
                bgColor = it
            }
            Text(
                text = pokemonList.name.uppercase(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomStart)
            )
        }

    }

}