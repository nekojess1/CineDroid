package com.studies.cinedroid.ui.home

import android.os.Build
import com.studies.cinedroid.model.BaseMoviesTest
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
}

