package com.studies.cinedroid.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.studies.cinedroid.data.repository.MovieRepository
import com.studies.cinedroid.utils.CoRuleTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseMoviesTest {

    val repository = mockk<MovieRepository>(relaxed = true)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coRuleTest = CoRuleTest()
}