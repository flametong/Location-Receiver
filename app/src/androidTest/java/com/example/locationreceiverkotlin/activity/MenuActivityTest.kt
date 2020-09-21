package com.example.locationreceiverkotlin.activity

import android.Manifest
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.example.locationreceiverkotlin.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MenuActivityTest{

    @Rule
    @JvmField
    var activityScenarioRule =
        ActivityScenarioRule(MenuActivity::class.java)

    @Rule
    @JvmField
    var mRunTimePermissionRule: GrantPermissionRule = GrantPermissionRule
        .grant(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    // Sign out test
    @Test
    fun testSignOut() {
        Espresso.onView(ViewMatchers.withId(R.id.btnSignOut))
            .perform(ViewActions.click())
        Thread.sleep(2000)
    }

    // Open google maps test
    @Test
    fun testOpenMap() {
        Espresso.onView(ViewMatchers.withId(R.id.btnShowCurrentLocation))
            .perform(ViewActions.click())
        Thread.sleep(2000)
    }
}