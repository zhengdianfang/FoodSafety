package com.zhengdianfang.foodsafety.main.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.SettingActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SettingFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<SettingActivity>(SettingActivity::class.java)

    @Test
    fun test_every_items_display() {
        onView(withId(R.id.navigationBarPopButton)).check(matches(isDisplayed()))
        onView(withText(R.string.setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.menu_style_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.feedback_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.about_us_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.share_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.evaluate_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.private_clause_setting_text)).check(matches(isDisplayed()))
        onView(withText(R.string.logout_text)).check(matches(isDisplayed()))
    }
}