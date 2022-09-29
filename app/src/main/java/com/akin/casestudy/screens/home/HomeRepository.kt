package com.akin.casestudy.screens.home

import com.akin.casestudy.data.ApiResult
import com.akin.casestudy.data.response.PokemonList

interface HomeRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResult<PokemonList>
}