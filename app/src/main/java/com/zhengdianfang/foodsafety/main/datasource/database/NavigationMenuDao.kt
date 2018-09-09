package com.zhengdianfang.foodsafety.main.datasource.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.MenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem

@Dao
interface NavigationMenuDao {

    @Insert(onConflict = REPLACE)
    fun saveAllMainMenus(items: List<MainMenuItem>)

    @Insert(onConflict = REPLACE)
    fun saveAllSubMenus(items: List<SubMenuItem>)

    @Query("SELECT * FROM  MainMenuItem")
    fun getMenuItems(): LiveData<List<MenuItem>>

    @Update(onConflict = REPLACE)
    fun updateMainMenu(mainMenuItem: MainMenuItem)

    @Update(onConflict = REPLACE)
    fun updateSubMenu(subMenuItem: SubMenuItem)
}