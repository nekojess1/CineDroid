package com.studies.cinedroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseMoviesTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coRuleTest = CoRuleTest()

    @get:Rule
    val rule = InstantTaskExecutorRule()
}
