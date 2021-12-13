package com.studies.cinedroid.ui.home

import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.model.BaseMoviesTest
import com.studies.cinedroid.ui.home.list.MovieListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListActivityViewModelTest : BaseMoviesTest() {
    private lateinit var viewModel: MovieListViewModel
    private val repository = mockk<MovieRepository>(relaxed = true)

    @Before
    internal fun setUp() {
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun `Get all popular movies returns successful`() {
        coEvery {
            repository.getPopularMovies()
        } returns mockk(relaxed = true)

        viewModel.loadPopularMovies()
        assertTrue(viewModel.popularMoviesList.value != null)
    }

    @Test
    fun `Get all popular movies returns error`() {
        coEvery {
            repository.getPopularMovies()
        }.throws(Throwable("Invalid data returned"))

        viewModel.loadPopularMovies()

        Assert.assertTrue(viewModel.errorLiveData.value != null)
    }

    @Test
    fun `Get all top movies returns successful`() {
        coEvery {
            repository.getTopMovies()
        } returns mockk(relaxed = true)

        viewModel.loadTopMovies()
        assertTrue(viewModel.topMoviesList.value != null)
    }

    @Test
    fun `Get all top movies returns error`() {
        coEvery {
            repository.getTopMovies()
        }.throws(Throwable("Invalid data returned"))

        viewModel.loadTopMovies()

        Assert.assertTrue(viewModel.errorLiveData.value != null)
    }
}