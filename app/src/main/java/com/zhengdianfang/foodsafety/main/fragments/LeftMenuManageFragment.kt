package com.zhengdianfang.foodsafety.main.fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_GRID_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_STYLE
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu_manage.*
import kotlinx.android.synthetic.main.navigation_bar_light_layout.*
import org.jetbrains.anko.find
import org.jetbrains.anko.forEachChildWithIndex
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import java.lang.ref.SoftReference

class LeftMenuManageFragment : BaseFragment() {
    private val checkedBackgroundColor by lazy {  ContextCompat.getColor(context!!, R.color.colorPrimary) }
    private val uncheckedBackgroundColor by lazy {  ContextCompat.getColor(context!!, android.R.color.transparent) }
    private val checkedTextColor by lazy {  ContextCompat.getColor(context!!, R.color.colorWhite) }
    private val uncheckedTextColor by lazy { ContextCompat.getColor(context!!, R.color.colorBlack) }
    private val menuManageHeaderView by lazy { LayoutInflater.from(context).inflate(R.layout.menu_manage_header_layout, null) }
    private val styleMenuRadioGroup by lazy { menuManageHeaderView.find<RadioGroup>(R.id.styleMenuRadioGroup) }
    private val mainLeftMenusViewModel by lazy { ViewModelProviders.of(this).get(MainLeftMenusViewModel::class.java) }
    private val menuItems = mutableListOf<MenuItem>()
    private lateinit var manageMenuAdapter: ManageMenuAdapter

    companion object {
        fun newInstance() = LeftMenuManageFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_manage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews()
        bindEvents()
        bindViewModel()
    }

    private fun initViews() {
        navigationTitleView.setText(R.string.style_manage_title)
        rightButton.setText(R.string.save_text)
        manageMenuAdapter = ManageMenuAdapter(menuItems)
        manageMenuAdapter.addHeaderView(menuManageHeaderView)
        manageRecyclerView.adapter = manageMenuAdapter

    }

    private fun bindEvents() {

        styleMenuRadioGroup.setOnCheckedChangeListener { radioGroup, componentId ->
            val radioButton = radioGroup.find<RadioButton>(componentId)
            resetRadioButtonStyle(radioGroup)
            when(componentId) {
                R.id.listStyleRadioButton-> {
                    radioButton.setBackgroundColor(checkedBackgroundColor)
                    radioButton.setTextColor(checkedTextColor)
                }
                R.id.gridStyleRadioButton -> {
                    radioButton.setBackgroundColor(checkedBackgroundColor)
                    radioButton.setTextColor(checkedTextColor)
                }
            }
        }
        val willCheckedRadioButtonId = if (defaultSharedPreferences.getString(LEFT_MENU_STYLE, LEFT_MENU_LIST_STYLE) == LEFT_MENU_LIST_STYLE)
            R.id.listStyleRadioButton else R.id.gridStyleRadioButton
        styleMenuRadioGroup.check(willCheckedRadioButtonId)

        rightButton.setOnClickListener {
            saveModifiedMenuItems()
        }
    }

    private fun saveModifiedMenuItems() {
        mainLeftMenusViewModel.updateMenuItems(menuItems)
        defaultSharedPreferences.edit().putString(
                LEFT_MENU_STYLE,
                if(styleMenuRadioGroup.checkedRadioButtonId == R.id.listStyleRadioButton ) LEFT_MENU_LIST_STYLE else LEFT_MENU_GRID_STYLE
        ).apply()
        pop()
    }

    private fun bindViewModel() {
        mainLeftMenusViewModel.updateMenuItems = { items ->
            menuItems.clear()
            menuItems.addAll(items)
            manageMenuAdapter.notifyDataSetChanged()
        }
        mainLeftMenusViewModel.initialNavigationMenus(SoftReference(this))
    }

    private fun resetRadioButtonStyle(radioGroup: RadioGroup) {
        radioGroup.forEachChildWithIndex { _, view ->
            view.setBackgroundColor(uncheckedBackgroundColor)
            (view as RadioButton).setTextColor(uncheckedTextColor)
        }
    }
}
