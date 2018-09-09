package com.zhengdianfang.foodsafety.common.matcher

import android.graphics.drawable.ColorDrawable
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeDiagnosingMatcher

class BackgroundColorDrawableMatcher(private val color: Int): TypeSafeDiagnosingMatcher<View>() {
    companion object {
        fun withBackgroundColor(color: Int): BackgroundColorDrawableMatcher {
            return BackgroundColorDrawableMatcher(color)
        }
    }
    override fun describeTo(description: Description?) {
        description?.appendText("with text color: ")
    }

    override fun matchesSafely(item: View?, mismatchDescription: Description?): Boolean {
        return color == (item?.background as ColorDrawable).color
    }
}