package com.zhengdianfang.foodsafety.setting.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.navigation_bar_dark_layout.*

class SettingFragment : BaseFragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigationTitleView.setText(R.string.setting_fragment_title)
        bindEvents()
    }

    private fun bindEvents() {
        menuStyleSettingView.setOnClickListener {
           start(LeftMenuManageFragment.newInstance())
        }
    }

}
