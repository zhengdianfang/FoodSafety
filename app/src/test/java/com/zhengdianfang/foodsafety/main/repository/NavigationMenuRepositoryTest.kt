package com.zhengdianfang.foodsafety.main.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhengdianfang.foodsafety.main.datasource.database.NavigationMenuDao
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch

@RunWith(MockitoJUnitRunner::class)
class NavigationMenuRepositoryTest {

    @Mock
    private lateinit var navigationMenuDao: NavigationMenuDao

    private lateinit var navigationMenuRepository: NavigationMenuRepository

    private val mockJson = "[{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"创建企业\",\n" +
            "    \"icon\": \"\",\n" +
            "    \"subMenuItems\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"厂房新建流程\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"经营证件办理\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 3,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"SC许可办理\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"原辅料合规判定\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 5,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"原辅料合规咨询\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 6,\n" +
            "        \"parentMenuId\": 1,\n" +
            "        \"name\": \"产品合规研判\",\n" +
            "        \"icon\": \"\",\n" +
            "        \"enable\": true\n" +
            "      }\n" +
            "    ]\n" +
            "  }]"

    @Before
    fun setUp() {
        navigationMenuRepository = NavigationMenuRepository(navigationMenuDao)
    }

    @Test
    fun test_initial_navigation_menus_data_when_no_record_in_database() {
        val countDownLatch = CountDownLatch(1)
        `when`(navigationMenuDao.getMenuItems()).thenReturn(listOf())
        navigationMenuRepository.initialMenuItemsIfNotCache(mockJson) { menuItems ->
            verify(navigationMenuDao).saveAllMainMenus(menuItems.map { MainMenuItem(it.id, it.name, it.icon) })
            val subMenuItems = mutableListOf<SubMenuItem>()
            menuItems.forEach { subMenuItems.addAll(it.subMenuItems!!) }
            verify(navigationMenuDao).saveAllSubMenus(subMenuItems.toList())
            assertEquals(menuItems.count(), 1)
            assertEquals(menuItems[0].subMenuItems?.count(), 6)
            countDownLatch.countDown()
        }
        countDownLatch.await()
    }

    @Test
    fun test_initial_navigation_menus_data_when_has_record_in_database() {
        val countDownLatch = CountDownLatch(1)
        val gson = Gson()
        var menuItems = gson.fromJson<List<MenuItem>>(mockJson, object : TypeToken<List<MenuItem>>() {}.type)
        `when`(navigationMenuDao.getMenuItems()).thenReturn(menuItems)
        navigationMenuRepository.initialMenuItemsIfNotCache(mockJson) { menuItems ->
            verify(navigationMenuDao, never()).saveAllMainMenus(menuItems.map { MainMenuItem(it.id, it.name, it.icon) })
            val subMenuItems = mutableListOf<SubMenuItem>()
            menuItems.forEach { subMenuItems.addAll(it.subMenuItems!!) }
            verify(navigationMenuDao, never()).saveAllSubMenus(subMenuItems.toList())
            assertEquals(menuItems.count(), 1)
            assertEquals(menuItems[0].subMenuItems?.count(), 6)
            countDownLatch.countDown()
        }
        countDownLatch.await()
    }
}