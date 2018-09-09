package com.zhengdianfang.foodsafety.main.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_LIST_STYLE
import com.zhengdianfang.foodsafety.main.constants.SharedPreferencesKeys.LEFT_MENU_STYLE
import com.zhengdianfang.foodsafety.main.dagger.DaggerMainLeftMenusViewModelComponent
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.repository.NavigationMenuRepository
import com.zhengdianfang.miracleframework.sharedPreference.SharedPreferenceStringLiveData
import com.zhengdianfang.miracleframework.sharedPreference.stringLiveData
import org.jetbrains.anko.defaultSharedPreferences
import java.io.*
import javax.inject.Inject

class MainLeftMenusViewModel(application: Application) : AndroidViewModel(application) {

    var menuItemsLiveData = MutableLiveData<MutableList<MenuItem>>()
    val menuStyleLiveData by lazy {
        val styleLiveData = getApplication<FoodSafetyApplication>().defaultSharedPreferences
                .stringLiveData(LEFT_MENU_STYLE, LEFT_MENU_LIST_STYLE)
        styleLiveData
    }

    @Inject
    lateinit var navigationMenuRepository: NavigationMenuRepository

    init {
        DaggerMainLeftMenusViewModelComponent.create().inject(this)
    }

    fun initialNavigationMenus() {
        navigationMenuRepository.initialMenuItemsIfNotCache(readAndParseMenuJson()) { menuItems ->
            menuItemsLiveData.postValue(menuItems.toMutableList())
        }
    }

    private fun readAndParseMenuJson(): String {
        var jsonContent = ""
        var inputStream: InputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        try {
            inputStream = getApplication<FoodSafetyApplication>().resources.openRawResource(R.raw.menus)
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
