package com.studies.cinedroid.di

import com.studies.cinedroid.BuildConfig
import com.studies.cinedroid.data.api.MovieAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object AppModule {
    val modules = listOf(MoviesModule.module, MovieViewModelModule.module)

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getRetroServiceInterface(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }
}
