package com.studies.cinedroid.data.api

import com.studies.cinedroid.domain.model.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopMovies(@Query("api_key") api_key: String): MoviesResponse
}