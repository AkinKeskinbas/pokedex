package com.akin.casestudy.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akin.casestudy.components.BaseAsyncImage
import com.akin.casestudy.navigation.Router
import timber.log.Timber


@Composable
fun HomeScreen(router: Router) {
    val viewModel: HomeViewModel = hiltViewModel()

    val state by viewModel.homeState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getPokemonList()
    }

    state.let { safeState ->
        when (safeState) {
            is HomeState.Initial -> {
                Timber.d("Successfully implemented")
            }
            is HomeState.Loading -> {}
            is HomeState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(safeState.pokemonList.orEmpty()) {
                        Column() {
                            BaseAsyncImage(image = it.image, modifier = Modifier.size(90.dp))
                            Text(
                                text = it.name,
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            is HomeState.Error -> {
                Timber.d(safeState.message.orEmpty())
            }
            is HomeState.Exception -> {}
            null -> {}
        }
    }

}