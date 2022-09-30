package com.akin.casestudy.screens.home

import com.akin.casestudy.data.ApiError
import com.akin.casestudy.data.ApiException
import com.akin.casestudy.data.ApiResult
import com.akin.casestudy.data.ApiSuccess
import com.akin.casestudy.data.model.PokemonViewItem
import com.akin.casestudy.data.response.PokemonList
import com.akin.casestudy.extansions.parseUrlToImage
import com.akin.casestudy.extansions.parseUrlToNumber
import com.akin.casestudy.util.Constants.TOTAL_SIZE
import javax.inject.Inject

class HomeMapper @Inject constructor() {
    fun mapPokemonList(response: ApiResult<PokemonList>): ApiResult<List<PokemonViewItem>> {
        return when (response) {
            is ApiSuccess -> {
                TOTAL_SIZE =  response.data.count
                ApiSuccess(response.data.results.map { result ->
                    val number = result.url.parseUrlToNumber()
                    val url = result.url.parseUrlToImage()
                    PokemonViewItem(
                        name = result.name,
                        image = url,
                        number = number
                    )
                })
            }
            is ApiError -> {
                ApiError(response.code, response.message)
            }
            is ApiException -> {
                ApiException(response.e)
            }
        }

    }
}