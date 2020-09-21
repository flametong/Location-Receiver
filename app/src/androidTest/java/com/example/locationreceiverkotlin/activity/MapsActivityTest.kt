package com.example.locationreceiverkotlin.activity

import android.Manifest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.example.locationreceiverkotlin.R
import org.junit.Rule
import org.junit.Test


class MapsActivityTest{

    @Rule
    @JvmField
    var activityScenarioRule =
        ActivityScenarioRule(MapsActivity::class.java)

    @Rule
    @JvmField
    var mRunTimePermissionRule: GrantPermissionRule = GrantPermissionRule
        .grant(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    @Test
    fun testCalendar() {
        onView(ViewMatchers.withId(R.id.btnOpenCalendar))
            .perform(click())
    }

}