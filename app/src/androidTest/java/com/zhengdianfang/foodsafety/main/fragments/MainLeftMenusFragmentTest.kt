package com.zhengdianfang.foodsafety.main.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.Gravity
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainLeftMenusFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open())
    }

    @Test
    fun test_display_left_menu_fragment_when_user_right_gesture() {

        onView(withText("创建企业")).check(matches(isDisplayed()))
        onView(withText("企业查询")).check(matches(isDisplayed()))
        onView(withText("标签管理")).check(matches(isDisplayed()))
        onView(withText("体系检查")).check(matches(isDisplayed()))
    }

    @Test
    fun test_display_left_menu_sub_menus_when_click_first_main_menu() {
        onView(withText("创建企业")).perform(click())
        onView(withText("厂房新建流程")).check(matches(isDisplayed()))
        onView(withText("经营证件办理")).check(matches(isDisplayed()))
        onView(withText("SC许可办理")).check(matches(isDisplayed()))
        onView(withText("原辅料合规判定")).check(matches(isDisplayed()))

    }
}