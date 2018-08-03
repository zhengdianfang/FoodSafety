package com.zhengdianfang.foodsafety.profile.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.profile.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FindPasswordFragmentTest {

    @JvmField
    @Rule val mockLoginActivity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun test_find_password_fragment_ui() {
        mockLoginActivity.activity
                .supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, FindPasswordFragment.newInstance())
                .commit()

        onView(withText(R.string.confirm_reset_password_button)).check(matches(isDisplayed()))
        onView(withHint(R.string.email_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.sms_code_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.password_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.confirm_password_hint)).check(matches(isDisplayed()))
    }
}