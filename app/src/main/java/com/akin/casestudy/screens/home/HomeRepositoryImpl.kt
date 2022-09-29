package com.akin.casestudy.screens.home

import com.akin.casestudy.data.ApiResult
import com.akin.casestudy.data.handleApi
import com.akin.casestudy.data.network.PokemonService
import com.akin.casestudy.data.response.PokemonList
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonService
) : HomeRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResult<PokemonList> =
        handleApi { pokemonService.getPokemonList(limit, offset) }
}