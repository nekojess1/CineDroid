package com.studies.cinedroid.model

import com.studies.cinedroid.utils.CoRuleTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.KoinTest
import org.robolectric.annotation.LooperMode

@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
abstract class BaseMoviesTest : KoinTest {

    @get:Rule
    var coRuleTest = CoRuleTest()

}