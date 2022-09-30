package com.akin.casestudy.screens.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.akin.casestudy.data.model.PokemonViewItem
import com.akin.casestudy.data.onError
import com.akin.casestudy.data.onException
import com.akin.casestudy.data.onSuccess
import com.akin.casestudy.util.Constants.EMPTY_STRING
import com.akin.casestudy.util.Constants.PAGE_SIZE
import com.akin.casestudy.util.Constants.TOTAL_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
) : ViewModel() {
    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Initial)
    val homeState: StateFlow<HomeState?> get() = _homeState
    var homePokeList = mutableStateOf<List<PokemonViewItem>>(listOf())
    var endReached = mutableStateOf(false)
    private var curPage = 0
    init {
        getPokemonList()
    }
    fun getPokemonList() {
        viewModelScope.launch {
           homeUseCase.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE).onSuccess { pokemonList ->
                _homeState.emit(HomeState.Success(pokemonList))
                endReached.value = curPage * PAGE_SIZE >= TOTAL_SIZE
                Timber.d("first: ${curPage * PAGE_SIZE} end: $TOTAL_SIZE")
                curPage++
                homePokeList.value += pokemonList
            }.onError { code, message ->
                _homeState.emit(HomeState.Error(code, message))
            }.onException { throwable ->
                _homeState.emit(HomeState.Exception(throwable))
            }

        }
    }
    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}

sealed class HomeState {
    object Loading : HomeState()
    object Initial : HomeState()
    class Success(
        val pokemonList: List<PokemonViewItem>? = null
    ) : HomeState()

    class Error(
        val code: Int = 0,
        val message: String? = EMPTY_STRING
    ) : HomeState()

    class Exception(
        val throwable: Throwable
    ) : HomeState()
}
