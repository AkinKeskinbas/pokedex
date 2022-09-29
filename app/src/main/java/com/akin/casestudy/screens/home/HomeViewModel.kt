package com.akin.casestudy.screens.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.model.PokemonViewItem
import com.akin.casestudy.data.onError
import com.akin.casestudy.data.onException
import com.akin.casestudy.data.onSuccess
import com.akin.casestudy.util.Constants.EMPTY_STRING
import com.akin.casestudy.util.Constants.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Initial)
    val homeState: StateFlow<HomeState?> get() = _homeState
    private var curPage = 0
    fun getPokemonList() {
        viewModelScope.launch {
            homeUseCase.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE).onSuccess { pokemonList ->
                _homeState.emit(HomeState.Success(pokemonList))
                curPage++
            }.onError { code, message ->
                _homeState.emit(HomeState.Error(code, message))
            }.onException { throwable ->
                _homeState.emit(HomeState.Exception(throwable))
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