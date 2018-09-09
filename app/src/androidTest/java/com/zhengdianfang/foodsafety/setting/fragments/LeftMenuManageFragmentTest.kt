package com.zhengdianfang.foodsafety.setting.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.content.ContextCompat
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.common.matcher.BackgroundColorDrawableMatcher
import com.zhengdianfang.foodsafety.common.matcher.TextColorMatcher
import com.zhengdianfang.foodsafety.setting.SettingActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LeftMenuManageFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<SettingActivity>(SettingActivity::class.java)

    private val menuManageFragment = LeftMenuManageFragment.newInstance()

    @Before
    fun setUp() {
        mockLoginActivity.activity
                .supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, menuManageFragment)
                .commit()
    }

    @Test
    fun test_menu_manage_fragment_uis() {
        onView(withText(R.string.style_manage_title)).check(matches(isDisplayed()))
        onView(withText(R.string.save_text)).check(matches(isDisplayed()))
        onView(withText(R.string.display_style_text)).check(matches(isDisplayed()))
        onView(withText(R.string.list_style_text)).check(matches(isDisplayed()))
        onView(withText(R.string.grad_style_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_menu_style_radio_button_switch() {
        onView(withId(R.id.listStyleRadioButton)).
                check(matches(TextColorMatcher.withTextColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorWhite))))

        onView(withId(R.id.listStyleRadioButton)).
                check(matches(BackgroundColorDrawableMatcher.withBackgroundColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorPrimary))))

        onView(withId(R.id.gridStyleRadioButton)).
                check(matches(TextColorMatcher.withTextColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorBlack))))

        onView(withId(R.id.gridStyleRadioButton)).
                check(matches(BackgroundColorDrawableMatcher.withBackgroundColor(ContextCompat.getColor(mockLoginActivity.activity, android.R.color.transparent))))

        onView(withId(R.id.gridStyleRadioButton)).perform(click())

        onView(withId(R.id.listStyleRadioButton)).
                check(matches(TextColorMatcher.withTextColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorBlack))))

        onView(withId(R.id.listStyleRadioButton)).
                check(matches(BackgroundColorDrawableMatcher.withBackgroundColor(ContextCompat.getColor(mockLoginActivity.activity, android.R.color.transparent))))

        onView(withId(R.id.gridStyleRadioButton)).
                check(matches(TextColorMatcher.withTextColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorWhite))))

        onView(withId(R.id.gridStyleRadioButton)).
                check(matches(BackgroundColorDrawableMatcher.withBackgroundColor(ContextCompat.getColor(mockLoginActivity.activity, R.color.colorPrimary))))
    }
}