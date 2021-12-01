package com.studies.cinedroid.di

import com.studies.cinedroid.BuildConfig
import com.studies.cinedroid.data.api.MovieAPI
import com.studies.cinedroid.data.repository.MovieRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MoviesModule {
    const val NAMED_API_KEY = "namedApiKey"
    val module = module {
        single {
            AppModule.getRetrofitInstance().create(MovieAPI::class.java)
        }
        single(named(NAMED_API_KEY)) {
            BuildConfig.API_KEY
        }
        single {
            MovieRepository(get(), get(named(NAMED_API_KEY)))
        }
    }
}