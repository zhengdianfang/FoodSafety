package com.zhengdianfang.foodsafety.main.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_GRID_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_left_menu_list.*
import org.jetbrains.anko.displayMetrics
import org.jetbrains.anko.find
import java.lang.ref.SoftReference

class MainLeftMenusFragment : BaseFragment() {

    private val mainLeftMenusViewModel by lazy { ViewModelProviders.of(this).get(MainLeftMenusViewModel::class.java) }
    private val menuItems = mutableListOf<MenuItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_left_menu_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews(SharedPreferencesKeys.LEFT_MENU_LIST_STYLE)
        initViewModel()
        bindEvents()
    }

    private fun initViews(style: String) {
        initRecyclerView(style)
        setViewWidth(style)
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
        mainLeftMenusViewModel.updateMenuItems = { items ->
            menuItems.clear()
            val filters = items.filter { menuItem -> menuItem.enable }
                    .map { menuItem ->
                        menuItem.subMenuItems = menuItem.subMenuItems?.filter { subMenuItem -> subMenuItem.enable }
                        menuItem
                    }
            menuItems.addAll(filters)
            leftMenuRecyclerView.adapter.notifyDataSetChanged()
        }

        mainLeftMenusViewModel.updateMenuStyle = { style ->
            initViews(style)
        }
        mainLeftMenusViewModel.observerMenuStyle(SoftReference(this))
        mainLeftMenusViewModel.initialNavigationMenus(SoftReference(this))
    }

    private fun initRecyclerView(style: String) {
        val leftMenuRecyclerViewAdapter = LeftMenuRecyclerViewAdapter(menuItems, style)
        leftMenuRecyclerViewAdapter.setHeaderView(initUserHeaderView())
        leftMenuRecyclerView.adapter = leftMenuRecyclerViewAdapter
        when (style) {
            LEFT_MENU_LIST_STYLE -> leftMenuRecyclerView.layoutManager = LinearLayoutManager(context)
            LEFT_MENU_GRID_STYLE -> {
                val gridLayoutManager = GridLayoutManager(context, 3)
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        val itemViewType = leftMenuRecyclerView.adapter.getItemViewType(position)
                        return if (itemViewType == MenuItem.MAIN_MENU_ITEM) gridLayoutManager.spanCount else 1
                    }

                }
                leftMenuRecyclerView.layoutManager = gridLayoutManager
            }
        }
    }

    private fun initUserHeaderView(): View {
        val headerView = LayoutInflater.from(context).inflate(R.layout.left_menu_user_info_layout, null)
        val avatarImageView = headerView.find<ImageView>(R.id.avatarImageView)
        Glide.with(this).load(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(avatarImageView)
        return headerView
    }

    private fun bindEvents() {
        settingButton.setOnClickListener {
            ARouter.getInstance().build("/setting/index").navigation(context)
        }
    }
}
