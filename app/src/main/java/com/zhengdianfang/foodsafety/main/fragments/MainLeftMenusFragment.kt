package com.zhengdianfang.foodsafety.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.BaseFragment

class MainLeftMenusFragment : BaseFragment() {

    private val mainLeftMenusViewModel by lazy { ViewModelProviders.of(this).get(MainLeftMenusViewModel::class.java) }
    private val menuItems = mutableListOf<MenuItem>()
    private val leftMenuRecyclerViewAdapter = LeftMenuRecyclerViewAdapter(menuItems)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_left_menu_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view as RecyclerView).adapter = leftMenuRecyclerViewAdapter
        mainLeftMenusViewModel.initialNavigationMenus()
        mainLeftMenusViewModel.menuItemsLiveData.observe(this, Observer<List<MenuItem>> { items ->
            if (items != null) {
                menuItems.clear()
                menuItems.addAll(items)
                leftMenuRecyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }
}
