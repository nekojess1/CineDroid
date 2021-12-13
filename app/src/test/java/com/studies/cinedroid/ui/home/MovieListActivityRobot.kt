package com.studies.cinedroid.ui.home

import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.MoviesResponse
import com.studies.cinedroid.ui.home.list.MovieListActivity
import com.studies.cinedroid.ui.home.list.MovieListViewModel
import com.studies.cinedroid.utils.robolectric.RobolectricRobotBase
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Assert.assertTrue
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module

internal class MovieListActivityRobot : RobolectricRobotBase() {
    private lateinit var subject: MovieListActivity
    private val repository = mockk<MovieRepository>()
    private val viewModel = MovieListViewModel(repository)

    private val followUpModule = module {
        factory { repository }
        factory { viewModel }
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

//        fun mockMoviesValues(values: MoviesResponse) {
////            every { repository.getTopMovies() } returns values
//        }
    }

    inner class Action {
        fun clickOnBackPressed() = subject.onBackPressed()
    }

    inner class Assert {
        fun assertActivityFinishCall() {
            assertTrue(subject.isFinishing)
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