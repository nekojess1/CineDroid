package com.studies.cinedroid.ui.home.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.Movies
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository) :
    ViewModel() {

    var popularMoviesList: MutableLiveData<List<Movies>> = MutableLiveData()
    var topMoviesList: MutableLiveData<List<Movies>> = MutableLiveData()

    fun getPopularMoviesObserver(): MutableLiveData<List<Movies>> {
        return popularMoviesList
    }

    fun getTopMoviesObserver(): MutableLiveData<List<Movies>> {
        return topMoviesList
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            runCatching {
                val response = repository.getPopularMovies()
                popularMoviesList.postValue(response.results)
            }.onFailure {
                popularMoviesList.postValue(null)
            }
        }
    }

    fun loadTopMovies() {
        viewModelScope.launch {
            runCatching {
                val response = repository.getTopMovies()
                topMoviesList.postValue(response.results)
            }.onFailure {
                topMoviesList.postValue(null)
            }
        }
    }
}