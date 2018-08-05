package com.zhengdianfang.foodsafety.profile.fragments

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.idling.CountingIdlingResource
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
class FindPasswordFragmentTest {

    @JvmField
    @Rule val mockLoginActivity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    private val findPasswordFragment = FindPasswordFragment.newInstance()

    @Before
    fun setUp() {
        mockLoginActivity.activity
                .supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, findPasswordFragment)
                .commit()
    }

    @After
    fun tearDown() {
        findPasswordFragment.toast?.cancel()
    }

    @Test
    fun test_find_password_fragment_ui() {

        onView(withText(R.string.find_password_title)).check(matches(isDisplayed()))
        onView(withText(R.string.confirm_reset_password_button)).check(matches(isDisplayed()))
        onView(withHint(R.string.email_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.sms_code_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.password_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.confirm_password_hint)).check(matches(isDisplayed()))
        onView(withId(R.id.resetPasswordButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_no_fill_username_toast_when_click_confirm_button() {
        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.email_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_no_fill_sms_code_toast_when_click_confirm_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("zdf@163.com"))
        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.sms_code_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_no_fill_password_toast_when_click_confirm_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("zdf@163.com"))
        onView(withId(R.id.smsCodeEdit)).perform(replaceText("111111"))
        onView(withId(R.id.newPasswordEdit)).perform(replaceText("123456"))

        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.confirm_password_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_success_toast_when_click_confirm_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("zdf@163.com"))
        onView(withId(R.id.smsCodeEdit)).perform(replaceText("111111"))
        onView(withId(R.id.newPasswordEdit)).perform(replaceText("111111"))
        onView(withId(R.id.confirmPasswordEdit)).perform(replaceText("111111"))

        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.reset_password_success_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_verify_email_format_when_input_username_edit_text() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("adfsdf"))
        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.email_format_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_should_verify_password_equal_when_input_both_password_edit_text() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("zdf@163.com"))
        onView(withId(R.id.smsCodeEdit)).perform(replaceText("111111"))
        onView(withId(R.id.newPasswordEdit)).perform(replaceText("111111"))
        onView(withId(R.id.confirmPasswordEdit)).perform(replaceText("2222"))

        onView(withId(R.id.resetPasswordButton)).perform(click())
        onView(withText(R.string.password_eqaul_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}