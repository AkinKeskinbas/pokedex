package com.akin.casestudy.di

import android.content.Context
import com.akin.casestudy.data.network.PokemonService
import com.akin.casestudy.screens.home.HomeRepository
import com.akin.casestudy.screens.home.HomeRepositoryImpl
import com.akin.casestudy.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(
        @ApplicationContext
        app: Context
    ): CaseStudyApplication {
        return app as CaseStudyApplication
    }


    @Singleton
    @Provides
    fun providePokeService(): PokemonService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository = homeRepository

}