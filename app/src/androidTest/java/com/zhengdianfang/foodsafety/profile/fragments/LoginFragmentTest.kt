package com.zhengdianfang.foodsafety.profile.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.common.matcher.ToastMatcher
import com.zhengdianfang.foodsafety.profile.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun test_login_fragment_ui() {
        onView(withHint(R.string.email_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.password_hint)).check(matches(isDisplayed()))
        onView(withText(R.string.login_button_text)).check(matches(isDisplayed()))
        onView(withText(R.string.just_register_text)).check(matches(isDisplayed()))
        onView(withText(R.string.find_password_title)).check(matches(isDisplayed()))
        onView(withText(R.string.third_login_title)).check(matches(isDisplayed()))
        onView(withId(R.id.weixinButton)).check(matches(isDisplayed()))
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_email_empty_tip_when_click_login_button() {
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText(R.string.email_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_email_illegal_format_tip_when_click_login_button() {
        onView(withId(R.id.emailEdit)).perform(replaceText("1212313"))
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText(R.string.email_format_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_password_empty_tip_when_click_login_button() {
        onView(withId(R.id.emailEdit)).perform(replaceText("123@163.com"))
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText(R.string.password_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_login_success_tip_when_click_login_button() {
        onView(withId(R.id.emailEdit)).perform(replaceText("123@163.com"))
        onView(withId(R.id.passwordEdit)).perform(replaceText("123456"))
        onView(withId(R.id.loginButton)).perform(click())
        onView(withText(R.string.login_success_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_start_find_password_fragment_when_click_find_password_button() {
        onView(withId(R.id.findPasswordView)).perform(click())
        onView(withText(R.string.confirm_reset_password_button)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        val topFragment = mockLoginActivity.activity.topFragment
        if (topFragment is LoginFragment) {
            topFragment.toast?.cancel()
        }
    }
}