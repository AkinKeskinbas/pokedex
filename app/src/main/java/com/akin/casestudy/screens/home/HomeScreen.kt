package com.akin.casestudy.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akin.casestudy.components.BaseAsyncImage
import com.akin.casestudy.extansions.isScrolledToTheEnd
import com.akin.casestudy.navigation.Router
import com.akin.casestudy.util.BaseLaunchedEffect
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import timber.log.Timber


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(router: Router) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.homeState.collectAsState()
    val endReached by remember { viewModel.endReached }
    val pokeList = viewModel.homePokeList.value

    state.let { safeState ->
        when (safeState) {
            is HomeState.Initial -> {
                Timber.d("Successfully implemented")
            }
            is HomeState.Loading -> {}
            is HomeState.Success -> {
                LazyRow(modifier = Modifier.padding(8.dp)) {
                    val itemCount = if (pokeList.size % 2 == 0) {
                        pokeList.size / 2
                    } else {
                        pokeList.size / 2 + 1
                    }
                    itemsIndexed(pokeList) { index, item ->
                        if (index >= itemCount - 1 && endReached.not()) {
                            LaunchedEffect(key1 = true) {
                                viewModel.getPokemonList()
                            }
                        }
                        PokemonCard(pokemonList = item, modifier = Modifier, viewModel = viewModel)
                        Spacer(modifier = Modifier.width(8.dp))
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