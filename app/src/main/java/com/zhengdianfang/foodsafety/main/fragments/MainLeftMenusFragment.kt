package com.zhengdianfang.foodsafety.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.BaseFragment
import org.jetbrains.anko.AnkoLogger

class MainLeftMenusFragment : BaseFragment() {

    private val mainLeftMenusViewModel by lazy { ViewModelProviders.of(this).get(MainLeftMenusViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_left_menu_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainLeftMenusViewModel.initialNavigationMenus()
        mainLeftMenusViewModel.menuItemsLiveData.observe(this, Observer<List<MenuItem>> {
            menuItems ->
            Log.d("MainLeftMenusFragment", "receive menus: ${menuItems.toString()}" )
        })
    }
}
