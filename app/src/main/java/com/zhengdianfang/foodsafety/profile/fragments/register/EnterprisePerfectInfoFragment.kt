package com.zhengdianfang.foodsafety.profile.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.navigation_bar_dark_layout.*

class EnterprisePerfectInfoFragment: BaseFragment() {

    companion object {
        fun newInstance() = EnterpriseRegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.enterprise_perfect_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigationTitleView.setText(R.string.perfect_info_title)
    }
}