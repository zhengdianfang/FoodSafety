package com.zhengdianfang.foodsafety.main.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.Gravity
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainLeftMenusFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun test_display_left_menu_fragment_when_user_right_gesture() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open())

//        val matcherMap = mapOf(Pair("STR", "创建企业"))
//        hasEntry(equalTo("STR"), `is`("创建企业"))
        Thread.sleep(10000)
    }
}