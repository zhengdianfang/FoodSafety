package com.zhengdianfang.foodsafety.main.datasource.database

import android.arch.persistence.room.Room import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.common.datasource.database.FoodSafetyDatabase
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual.equalTo

@LargeTest
@RunWith(AndroidJUnit4::class)
class NavigationMenuDaoTest {

    private lateinit var foodSafetyDatabase: FoodSafetyDatabase
    private lateinit var navigationMenuDao: NavigationMenuDao

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        foodSafetyDatabase = Room.inMemoryDatabaseBuilder(context, FoodSafetyDatabase::class.java).build()
        navigationMenuDao = foodSafetyDatabase.navigationMenuDao()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        foodSafetyDatabase.close()
    }

    @Test
    fun test_save_main_menus() {
        val menus = listOf(
                MainMenuItem("menu1", "icon1")
        )
        navigationMenuDao.saveAllMainMenus(menus)
        val menuItems = navigationMenuDao.getMenuItems()
        assertThat(menuItems.count(), `is`(1))
        assertThat(menuItems[0].id, `is`(1))
    }

    @Test
    fun test_save_sub_menus() {
        val menus = listOf(
                MainMenuItem("menu1", "icon1")
        )
        navigationMenuDao.saveAllMainMenus(menus)
        val subMenus = listOf(
                SubMenuItem(1, "subMenu1", "icon1", true),
                SubMenuItem(1, "subMenu2", "icon2", true),
                SubMenuItem(1, "subMenu3", "icon3", false)
        )
        navigationMenuDao.saveAllSubMenus(subMenus)
        val menuItems = navigationMenuDao.getMenuItems()
        assertThat(menuItems[0].subMenuItems?.count(), `is`(3))
        assertThat(menuItems[0].subMenuItems?.get(0), equalTo(
                SubMenuItem(1, "subMenu1", "icon1", true))
        )
    }
}