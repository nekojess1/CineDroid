package com.studies.cinedroid.ui.home

import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.Movies
import com.studies.cinedroid.domain.model.response.MoviesResponse
import com.studies.cinedroid.ui.home.list.MovieListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieListActivityViewModelTest {
    private val repository = mockk<MovieRepository>()
    private val onDataLoadedObserver = mockk<Observer<List<Movies>>>(relaxed = true)

    @get:Rule
    val rule :
    @Test
    fun `when view model fetches data then it should call the repository`() {
        val viewModel = instantiateViewModel()
        val movie = Movies(
            false, "", listOf(2), 4, "`PT",
            "Teste Mockk", "Testando", 10.0, "", "",
            "Teste android", false, 5.0, 3
        )
        val mockedList = MoviesResponse(2, listOf(movie))

        coEvery {
            repository.getPopularMovies()
        } returns mockedList

        viewModel.loadTopMovies()
        verify { viewModel.loadTopMovies() }
    }

    private fun instantiateViewModel(): MovieListViewModel {
        val viewModel = MovieListViewModel(repository)
        return viewModel
    }
}