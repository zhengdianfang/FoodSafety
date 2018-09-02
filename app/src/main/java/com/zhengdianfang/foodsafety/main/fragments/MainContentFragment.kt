package com.zhengdianfang.foodsafety.main.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_GRID_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_STYLE
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_content.*
import org.jetbrains.anko.support.v4.defaultSharedPreferences

class MainContentFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textTextView.setOnClickListener {
            val style = defaultSharedPreferences.getString(LEFT_MENU_STYLE, LEFT_MENU_LIST_STYLE)
            if (style == LEFT_MENU_LIST_STYLE) {
                defaultSharedPreferences.edit().putString(LEFT_MENU_STYLE, LEFT_MENU_GRID_STYLE).apply()
            } else {
                defaultSharedPreferences.edit().putString(LEFT_MENU_STYLE, LEFT_MENU_LIST_STYLE).apply()
            }
        }
    }

}
