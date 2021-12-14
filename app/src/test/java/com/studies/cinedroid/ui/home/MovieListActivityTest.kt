package com.studies.cinedroid.ui.home

import android.os.Build
import com.studies.cinedroid.domain.model.response.Movies
import com.studies.cinedroid.model.BaseMoviesTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.Q])
class MovieListActivityTest : BaseMoviesTest() {
    private lateinit var robot: MovieListActivityRobot

    @Before
    fun setUp() {
        robot = MovieListActivityRobot()
    }

    @After
    fun tearDown() {
        robot.tearDown()
    }

    @Test
    fun `when the native back button is clicked, the activity must end`() {
        with(robot) {
            arrange {
                launch()
            }
            action {
                clickOnBackPressed()
            }
            assert {
                assertActivityFinishCall()
            }
        }
    }

    @Test
    fun `when the item of recycler view is clicked, the MovieDetailsActivity Must show`() {
        with(robot) {
            arrange {
                launch()
            }
            action {
                clickRecyclerView()
            }
            assert {
                assertInitActivity()
            }
        }
    }

    @Test
    fun `when injection list movies, of the list is equal expected`() {
        with(robot) {
            arrange {
                mockMoviesValues(mockList)
                launch()
            }
            action {
            }
            assert {
                assertMovies(mockList.size)
            }
        }
    }

    @Test
    fun `when injection empty list movies, of the list is empty`() {
        with(robot) {
            arrange {
                mockMoviesValues(emptyList)
                launch()
            }
            action {
            }
            assert {
                assertMovies(emptyList.size)
            }
        }
    }

    companion object {
        private val emptyList = arrayListOf<Movies>()
        private val mockList = arrayListOf<Movies>(
            mockk(relaxed = true),
            mockk(relaxed = true),
            mockk(relaxed = true)
        )
    }
}

