package com.zhengdianfang.foodsafety.profile.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.BaseFragment

class FindPasswordFragment : BaseFragment() {

    companion object {
        fun newInstance() = FindPasswordFragment()
    }

    private lateinit var viewModel: FindPasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.find_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FindPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
