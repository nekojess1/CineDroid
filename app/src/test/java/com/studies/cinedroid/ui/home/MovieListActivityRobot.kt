package com.studies.cinedroid.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.studies.cinedroid.R
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.Movies
import com.studies.cinedroid.ui.home.list.MovieListActivity
import com.studies.cinedroid.ui.home.list.MovieListViewModel
import com.studies.cinedroid.utils.robolectric.RobolectricRobotBase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Assert.assertTrue
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.assertEquals

internal class MovieListActivityRobot : RobolectricRobotBase() {
    private lateinit var subject: MovieListActivity
    private val repository = mockk<MovieRepository>()
    private val viewModel = MovieListViewModel(repository)

    private val followUpModule = module {
        factory { repository }
        factory { viewModel }
    }

    private val recyclerView: RecyclerView by lazy {
        subject.findViewById(R.id.moviesList)
    }

    init {
        loadKoinModules(followUpModule)
    }

    inner class Arrange {
        fun launch() {
            buildActivity<MovieListActivity>().run {
                subject = spyActivity
            }
        }

        fun mockMoviesValues(values: List<Movies>) {
            coEvery {
                repository.getPopularMovies().results
            } returns values
        }
    }

    inner class Action {
        fun clickOnBackPressed() = subject.onBackPressed()
        fun clickRecyclerView() = recyclerView.getChildAt(0).performClick()
    }

    inner class Assert {
        fun assertActivityFinishCall() {
            assertTrue(subject.isFinishing)
        }

        fun assertMovies(listSize: Int) {
            assertEquals(listSize, recyclerView.adapter?.itemCount)
        }
    }

    fun tearDown() {
        unmockkAll()
        stopKoin()
        subject.finish()
    }

    fun arrange(func: Arrange.() -> Unit) = Arrange().apply(func)
    fun action(func: Action.() -> Unit) = Action().apply(func)
    fun assert(func: Assert.() -> Unit) = Assert().apply(func)
}