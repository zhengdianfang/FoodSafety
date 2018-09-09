package com.zhengdianfang.foodsafety.profile.fragments.register


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.navigation_bar_dark_layout.*

class RegisterFragment : BaseFragment() {

    private val enterpriseRegisterFragment = EnterpriseRegisterFragment.newInstance()
    private val personalRegisterFragment = PersonalRegisterFragment.newInstance()

    private val tabBackgroundSelector = mapOf(
            Pair("active", R.drawable.drawableBtnNormalColor),
            Pair("unActive", R.drawable.drawableUnActive)
    )

    private val tabTextColorSelector = mapOf(
            Pair("active", R.color.colorWhite),
            Pair("unActive", R.color.colorUnActiveText)
    )

    private val tabs by lazy {  arrayOf(enterpriseUserTabView, personalUserTabView) }

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navigationTitleView.setText(R.string.register_title)

        loadMultipleRootFragment(R.id.registerContainerView, 0,  enterpriseRegisterFragment, personalRegisterFragment)

        enterpriseUserTabView.setOnClickListener {
            switchTab(it)
            showHideFragment(enterpriseRegisterFragment, personalRegisterFragment)
        }

        personalUserTabView.setOnClickListener {
            switchTab(it)
            showHideFragment(personalRegisterFragment, enterpriseRegisterFragment)
        }
    }

    private fun switchTab(view: View) {
        tabs.forEach { tabView ->
           if (view.id == tabView.id) {
               tabView.setBackgroundResource(tabBackgroundSelector["active"]!!)
               tabView.setTextColor(tabTextColorSelector["active"]!!)
           } else {
               tabView.setBackgroundResource(tabBackgroundSelector["unActive"]!!)
               tabView.setTextColor(tabTextColorSelector["unActive"]!!)
           }
        }
    }
}
