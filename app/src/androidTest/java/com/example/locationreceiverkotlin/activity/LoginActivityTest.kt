package com.example.locationreceiverkotlin.activity

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.locationreceiverkotlin.R
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private var emailTest = "nikpul11@gmail.com"
    private var passwordTest = "2001ybrbnf"
    private var wrongPassword = "qwerty12345"

    @Rule
    @JvmField
    var activityScenarioRule =
        ActivityScenarioRule(LoginActivity::class.java)

    @Rule
    @JvmField
    var mRunTimePermissionRule: GrantPermissionRule = GrantPermissionRule
        .grant(
            Manifest.permission.INTERNET
        )

    // Test with empty fields
    @Test
    fun testEmptyFields() {
        onView(withId(R.id.btnLogin)).perform(click())
        Thread.sleep(2000)
    }

    // Test with wrong password
    @Test
    fun testWrongFields() {
        onView(withId(R.id.edEmail))
            .perform(replaceText(emailTest), closeSoftKeyboard())

        onView(withId(R.id.edPassword))
            .perform(replaceText(wrongPassword), closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())
        Thread.sleep(2000)
    }

    // Test with correct password
    @Test
    fun testWriteFields() {
        onView(withId(R.id.edEmail))
            .perform(replaceText(emailTest), closeSoftKeyboard())

        onView(withId(R.id.edPassword))
            .perform(replaceText(passwordTest), closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())
        Thread.sleep(2000)
    }
}