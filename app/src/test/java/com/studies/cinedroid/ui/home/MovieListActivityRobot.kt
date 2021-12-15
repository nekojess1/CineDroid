package com.studies.cinedroid.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.studies.cinedroid.R
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.domain.model.response.Movies
import com.studies.cinedroid.domain.model.response.MoviesResponse
import com.studies.cinedroid.ui.home.details.MovieDetailsActivity
import com.studies.cinedroid.ui.home.list.MovieListActivity
import com.studies.cinedroid.ui.home.list.MovieListViewModel
import com.studies.cinedroid.utils.robolectric.RobolectricRobotBase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.Assert.assertTrue
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.robolectric.shadows.ShadowActivity
import kotlin.test.assertEquals

internal class MovieListActivityRobot : RobolectricRobotBase() {
    private lateinit var subject: MovieListActivity
    private var shadow: ShadowActivity? = null
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
                shadow = shadowActivity
            }
        }

        fun mockMoviesValues(values: List<Movies>) {
            coEvery {
                repository.getPopularMovies()
            } returns mockk {
                every { results } returns values
            }
        }
    }

    inner class Action {
        fun clickOnBackPressed() = subject.onBackPressed()
        fun clickRecyclerView() = recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
    }

    inner class Assert {
        fun assertActivityFinishCall() {
            assertTrue(subject.isFinishing)
        }

        fun assertMovies(listSize: Int) {
            assertEquals(listSize, recyclerView.adapter?.itemCount)
        }

        fun assertInitActivity() {
            val intent = shadow?.peekNextStartedActivity()
            assertEquals(
                MovieDetailsActivity::class.java.canonicalName, intent?.component?.className
            )
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