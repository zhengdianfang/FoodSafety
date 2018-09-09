package com.zhengdianfang.foodsafety.main.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Observer
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_STYLE
import com.zhengdianfang.foodsafety.main.dagger.DaggerMainLeftMenusViewModelComponent
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.repository.NavigationMenuRepository
import com.zhengdianfang.miracleframework.BaseFragment
import com.zhengdianfang.miracleframework.adapter.base.entity.MultiItemEntity
import com.zhengdianfang.miracleframework.sharedPreference.stringLiveData
import org.jetbrains.anko.defaultSharedPreferences
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.ref.SoftReference
import javax.inject.Inject

class MainLeftMenusViewModel(application: Application) : AndroidViewModel(application) {

    val context by lazy { getApplication<FoodSafetyApplication>() }

    var updateMenuItems: ((items: List<MenuItem>) -> Unit)? = null
    var updateMenuStyle: ((style: String) -> Unit)? = null

    @Inject
    lateinit var navigationMenuRepository: NavigationMenuRepository

    init {
        DaggerMainLeftMenusViewModelComponent.create().inject(this)
    }

    fun initialNavigationMenus(reference: SoftReference<BaseFragment>) {
        navigationMenuRepository.initialMenuItemsIfNotCache(readAndParseMenuJson()) { liveData ->
            val fragment = reference.get()
            if (fragment != null) {
                liveData.observe(fragment, Observer { items ->
                    if (items != null) {
                        updateMenuItems?.invoke(items)
                    }
                })
            }
        }
    }

    fun observerMenuStyle(reference: SoftReference<BaseFragment>) {
        val styleLiveData = context.defaultSharedPreferences.stringLiveData(LEFT_MENU_STYLE, LEFT_MENU_LIST_STYLE)
        val fragment = reference.get()
        if (fragment != null) {
            styleLiveData.observe(fragment, Observer { style ->
                updateMenuStyle?.invoke(style!!)
            })
        }
    }

    fun updateMenuItems(menuItems: List<MultiItemEntity>) {
        val filter = menuItems.filter { it.itemType == MenuItem.MAIN_MENU_ITEM }.map { it as MenuItem }
        navigationMenuRepository.updateMenuItems(filter)
    }

    private fun readAndParseMenuJson(): String {
        var jsonContent = ""
        var inputStream: InputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        try {
            inputStream = context.resources.openRawResource(R.raw.menus)
            inputStreamReader = InputStreamReader(inputStream)
            bufferedReader = BufferedReader(inputStreamReader)
            jsonContent = bufferedReader.readText()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            inputStreamReader?.close()
            bufferedReader?.close()
        }
        return jsonContent
    }
}
