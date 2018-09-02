package com.zhengdianfang.foodsafety.main.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPerferencesKeys.LEFT_MENU_GRID_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPerferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.BaseFragment
import org.jetbrains.anko.displayMetrics

class MainLeftMenusFragment : BaseFragment() {

    private val mainLeftMenusViewModel by lazy { ViewModelProviders.of(this).get(MainLeftMenusViewModel::class.java) }
    private val menuItems = mutableListOf<MenuItem>()
    private val leftMenuRecyclerViewAdapter by lazy { LeftMenuRecyclerViewAdapter(menuItems, mainLeftMenusViewModel.menuStyleLiveData.value!!) }
    private val leftMenuRecyclerView by lazy { view as RecyclerView }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_left_menu_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        leftMenuRecyclerView.adapter = leftMenuRecyclerViewAdapter
        setLayoutManagerToRecyclerView(mainLeftMenusViewModel.menuStyleLiveData.value!!)
        setViewWidth(mainLeftMenusViewModel.menuStyleLiveData.value!!)
    }

    private fun setViewWidth(style: String) {
        when(style) {
            LEFT_MENU_LIST_STYLE -> {
                val layoutParams = view?.layoutParams as DrawerLayout.LayoutParams
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                view?.layoutParams = layoutParams
            }
            LEFT_MENU_GRID_STYLE -> {
                val layoutParams = view?.layoutParams as DrawerLayout.LayoutParams
                layoutParams.width = context?.displayMetrics?.widthPixels!!
                view?.layoutParams = layoutParams
            }
        }
    }

    private fun initViewModel() {
        mainLeftMenusViewModel.initialNavigationMenus()
        mainLeftMenusViewModel.menuItemsLiveData.observe(this, Observer<List<MenuItem>> { items ->
            if (items != null) {
                menuItems.clear()
                menuItems.addAll(items)
                leftMenuRecyclerViewAdapter.notifyDataSetChanged()
            }
        })

        mainLeftMenusViewModel.menuStyleLiveData.observe(this, Observer<String> { style ->
            setLayoutManagerToRecyclerView(style!!)
        })
    }

    private fun setLayoutManagerToRecyclerView(style: String) {
        when (style) {
            LEFT_MENU_LIST_STYLE -> leftMenuRecyclerView.layoutManager = LinearLayoutManager(context)
            LEFT_MENU_GRID_STYLE -> {
                val gridLayoutManager = GridLayoutManager(context, 3)
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val itemViewType = leftMenuRecyclerViewAdapter.getItemViewType(position)
                        return if (itemViewType == MenuItem.MAIN_MENU_ITEM) gridLayoutManager.spanCount else 1
                    }

                }
                leftMenuRecyclerView.layoutManager = gridLayoutManager
            }
        }
    }
}
