package com.akin.casestudy.screens.home

import com.akin.casestudy.data.ApiResult
import com.akin.casestudy.data.model.PokemonViewItem
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeMapper: HomeMapper,
    private val homeRepository: HomeRepository
) {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResult<List<PokemonViewItem>> {
        return homeMapper.mapPokemonList(homeRepository.getPokemonList(limit, offset))
    }
}