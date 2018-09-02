package com.zhengdianfang.foodsafety.main.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.main.dagger.DaggerMainLeftMenusViewModelComponent
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.repository.NavigationMenuRepository
import java.io.*
import javax.inject.Inject

class MainLeftMenusViewModel(application: Application) : AndroidViewModel(application) {

    var menuItemsLiveData = MutableLiveData<List<MenuItem>>()

    @Inject
    lateinit var navigationMenuRepository: NavigationMenuRepository


    init {
        DaggerMainLeftMenusViewModelComponent.create().inject(this)
    }

    fun initialNavigationMenus() {
        navigationMenuRepository.initialMenuItemsIfNotCache(readAndParseMenuJson()) { menuItems ->
            menuItemsLiveData.postValue(menuItems)
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
            jsonContent = bufferedReader?.readText()
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
