package com.studies.cinedroid.ui.home.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.Movies
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository) :
    ViewModel() {

    var popularMoviesList: MutableLiveData<List<Movies>> = MutableLiveData()

    private val errorMutableLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = errorMutableLiveData


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
                errorMutableLiveData.value = it.message ?: it.localizedMessage ?: ""
            }
        }
    }

    fun loadTopMovies() {
        viewModelScope.launch {
            runCatching {
                val response = repository.getTopMovies()
                topMoviesList.postValue(response.results)
            }.onFailure {
                errorMutableLiveData.value = it.message ?: it.localizedMessage ?: ""
            }
        }
    }
}