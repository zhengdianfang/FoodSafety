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
                MainMenuItem(1,"menu1", "icon1")
        )
        navigationMenuDao.saveAllMainMenus(menus)
        val menuItems = navigationMenuDao.getMenuItems().value
        assertThat(menuItems?.count(), `is`(1))
        assertThat(menuItems?.get(0)?.id, `is`(1))
    }

    @Test
    fun test_save_sub_menus() {
        val menus = listOf(
                MainMenuItem(1,"menu1", "icon1")
        )
        navigationMenuDao.saveAllMainMenus(menus)
        val subMenus = listOf(
                SubMenuItem(1, 1, "subMenu1", "icon1", true),
                SubMenuItem(2, 1, "subMenu2", "icon2", true),
                SubMenuItem(3, 1, "subMenu3", "icon3", false)
        )
        navigationMenuDao.saveAllSubMenus(subMenus)
        val menuItems = navigationMenuDao.getMenuItems().value
        assertThat(menuItems?.first()?.subMenuItems?.count(), `is`(3))
        assertThat(menuItems?.first()?.subMenuItems?.get(0), equalTo(
                SubMenuItem(1, 1, "subMenu1", "icon1", true))
        )
    }

    @Test
    fun test_update_main_menu() {
        test_save_main_menus()
        val newMainMenu = MainMenuItem(1,"menu2", "icon2", false)
        navigationMenuDao.updateMainMenu(newMainMenu)
        val menuItems = navigationMenuDao.getMenuItems().value
        assertThat(menuItems?.count(), `is`(1))
        assertThat(menuItems?.first()?.name, `is`("menu2"))
        assertThat(menuItems?.first()?.enable, `is`(false))
    }

    @Test
    fun test_update_sub_menu() {
        test_save_sub_menus()
        val newSubMenu = SubMenuItem(1, 1,"menu2", "icon2", false)
        navigationMenuDao.updateSubMenu(newSubMenu)
        val menuItems = navigationMenuDao.getMenuItems().value
        assertThat(menuItems?.first()?.subMenuItems?.count(), `is`(3))
        assertThat(menuItems?.first()?.subMenuItems?.get(0)?.name, `is`("menu2"))
        assertThat(menuItems?.first()?.subMenuItems?.get(0)?.enable, `is`(false))
    }
}