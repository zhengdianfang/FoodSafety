package com.zhengdianfang.foodsafety.common.matcher

import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import android.widget.TextView
import org.hamcrest.Description

class TextColorMatcher(private val color: Int): BoundedMatcher<View, TextView>(TextView::class.java) {

    companion object {
        fun withTextColor(color: Int): TextColorMatcher {
            return TextColorMatcher(color)
        }
    }
    override fun describeTo(description: Description?) {
        description?.appendText("with text color: ")
    }
    override fun matchesSafely(item: TextView?): Boolean {
        return color == item?.currentTextColor
    }
}