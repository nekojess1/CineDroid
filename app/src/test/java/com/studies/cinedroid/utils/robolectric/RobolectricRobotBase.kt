package com.studies.cinedroid.utils.robolectric

import android.app.Activity
import android.content.Intent
import io.mockk.spyk
import org.robolectric.Robolectric
import org.robolectric.Shadows
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowActivity

abstract class RobolectricRobotBase {

    data class RobolectricData<A : Activity>(
        val controller: ActivityController<A>,
        val shadowActivity: ShadowActivity?,
        val spyActivity: A

    )

    inline fun <reified A : Activity> buildActivity(intent: Intent = Intent()): RobolectricData<A> {

        return getController<A>(intent).run {
            RobolectricData(
                controller = this,
                shadowActivity = getShadowActivity(this),
                spyActivity = get()
            )
        }
    }

    inline fun <reified A : Activity> getController(intent: Intent): ActivityController<A> {
        return Robolectric.buildActivity(A::class.java, intent)
    }

    inline fun <reified A : Activity> getShadowActivity(
        controller: ActivityController<A>
    ): ShadowActivity? {
        return spyk(controller.setup().get(), recordPrivateCalls = true)?.run {
            Shadows.shadowOf(this)
        }
    }
}