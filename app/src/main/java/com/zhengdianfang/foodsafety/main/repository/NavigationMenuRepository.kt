package com.zhengdianfang.foodsafety.main.repository

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

    fun initialMenuItemsIfNotCache(@NotNull menusJson: String, @NotNull initialCallback: (menuItems: List<MenuItem>) -> Unit) {
        doAsync {
            var menuItems = navigationMenuDao.getMenuItems()
            if (menuItems.isEmpty()) {
                val gson = Gson()
                menuItems = gson.fromJson<List<MenuItem>>(menusJson, object : TypeToken<List<MenuItem>>() {}.type)
                if (menuItems.isNotEmpty()) {
                    navigationMenuDao.saveAllMainMenus(
                            menuItems.map { menuItem -> MainMenuItem(menuItem.id, menuItem.name, menuItem.icon) }
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
            initialCallback(menuItems)
        }
    }
}