package com.zhengdianfang.foodsafety.profile.fragments.register

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.profile.RegisterActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RegisterFragmentTest {

    @JvmField
    @Rule
    val mockRegisterActivity = ActivityTestRule<RegisterActivity>(RegisterActivity::class.java)

    @Test
    fun test_register_fragment_ui() {
        onView(withText(R.string.enterprise_user_text)).check(matches(isDisplayed()))
        onView(withText(R.string.person_user_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_switch_tab() {
        onView(withId(R.id.enterpriseUserTabView)).perform(click())
        onView(withHint(R.string.email_or_phone_hint)).check(matches(isDisplayed()))
        onView(withText("PersonalRegisterFragment")).check(matches(not(isDisplayed())))

        onView(withId(R.id.personalUserTabView)).perform(click())
        onView(withText("PersonalRegisterFragment")).check(matches(isDisplayed()))
        onView(withHint(R.string.email_or_phone_hint)).check(matches(not(isDisplayed())))
    }
}