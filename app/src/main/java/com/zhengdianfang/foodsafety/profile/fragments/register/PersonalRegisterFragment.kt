package com.zhengdianfang.foodsafety.profile.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.BaseFragment

class PersonalRegisterFragment : BaseFragment() {

    companion object {
        fun newInstance() = PersonalRegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.personal_regsiter_fragment, container, false)
    }
}