package com.zhengdianfang.foodsafety.profile.fragments.register

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
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EnterpriseRegisterFragmentTest {

    @JvmField
    @Rule
    val mockLoginActivity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    private val registerFragment = RegisterFragment.newInstance()

    @Before
    fun setUp() {
        mockLoginActivity.activity
                .supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, registerFragment)
                .commit()
    }

    @Test
    fun test_enterprise_register_fragment_ui() {
        onView(withHint(R.string.email_or_phone_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.password_hint)).check(matches(isDisplayed()))
        onView(withHint(R.string.confirm_password_hint)).check(matches(isDisplayed()))
        onView(withText(R.string.next_step_text)).check(matches(isDisplayed()))
        onView(withText(R.string.exist_account_just_login_text)).check(matches(isDisplayed()))
    }

    @Test
    fun test_account_empty_tip_when_click_next_button() {
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.email_or_phone_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_account_illegal_format_tip_when_click_next_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("adfsdfsfd"))
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.email_or_phone_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_password_empty_tip_when_click_next_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("18511112222"))
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.password_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_confirm_password_empty_tip_when_click_next_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("18511112222"))
        onView(withId(R.id.passwordEdit)).perform(replaceText("123445"))
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.confirm_password_hint)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_password_unequal_tip_when_click_next_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("18511112222"))
        onView(withId(R.id.passwordEdit)).perform(replaceText("123445"))
        onView(withId(R.id.confirmPasswordEdit)).perform(replaceText("werwer"))
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.password_eqaul_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @Test
    fun test_register_success_tip_when_click_next_button() {
        onView(withId(R.id.userNameEdit)).perform(replaceText("18511112222"))
        onView(withId(R.id.passwordEdit)).perform(replaceText("123445"))
        onView(withId(R.id.confirmPasswordEdit)).perform(replaceText("123445"))
        onView(withId(R.id.nextStepButton)).perform(click())
        onView(withText(R.string.register_success_toast)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        val enterpriseRegisterFragment = registerFragment.findChildFragment(EnterpriseRegisterFragment::class.java)
        if (enterpriseRegisterFragment != null) {
            enterpriseRegisterFragment.toast?.cancel()
        }
    }
}