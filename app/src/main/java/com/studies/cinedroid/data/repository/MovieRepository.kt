package com.studies.cinedroid.data.repository

import com.studies.cinedroid.data.api.MovieAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private var service: MovieAPI, private val apiKey: String) {

    suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        service.getPopularMovies(apiKey)
    }

    suspend fun getTopMovies() = withContext(Dispatchers.IO) {
        service.getTopMovies(apiKey)
    }
}
