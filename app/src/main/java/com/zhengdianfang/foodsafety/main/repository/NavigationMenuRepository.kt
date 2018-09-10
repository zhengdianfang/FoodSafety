package com.zhengdianfang.foodsafety.main.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhengdianfang.foodsafety.main.datasource.database.NavigationMenuDao
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import org.jetbrains.anko.doAsync
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

class NavigationMenuRepository @Inject constructor(private val navigationMenuDao: NavigationMenuDao) {

    fun initialMenuItemsIfNotCache(@NotNull menusJson: String, @NotNull initialCallback: (menuItems: LiveData<List<MenuItem>>) -> Unit) {
        doAsync {
            var menuItems = navigationMenuDao.getMenuItems()
            if (menuItems.isEmpty()) {
                val gson = Gson()
                val menuItems = gson.fromJson<List<MenuItem>>(menusJson, object : TypeToken<List<MenuItem>>() {}.type)
                if (menuItems.isNotEmpty()) {
                    navigationMenuDao.saveAllMainMenus(
                            menuItems.map { menuItem -> menuItem.createMainMenuItem() }
                    )
                    val subMenuItems = mutableListOf<SubMenuItem>()
                    menuItems.forEach { menuItem ->
                        if (menuItem.subMenuItems != null) {
                            subMenuItems.addAll(menuItem.subMenuItems!!)
                        }
                    }
                    navigationMenuDao.saveAllSubMenus(subMenuItems.toList())
                }
            }
            initialCallback(navigationMenuDao.getMenuItemsLiveData())
        }
    }

    fun updateMenuItems(menuItems: List<MenuItem>) {
        doAsync {
            navigationMenuDao.saveAllMainMenus(
                    menuItems.map { menuItem -> menuItem.createMainMenuItem() }
            )
            val subMenus = mutableListOf<SubMenuItem>()

            menuItems.forEach { menuItem ->
                subMenus.addAll(menuItem.subMenuItems!!)
            }
            navigationMenuDao.saveAllSubMenus(subMenus)
        }
    }
}