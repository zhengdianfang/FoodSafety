package com.zhengdianfang.foodsafety.common.dagger

import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.common.datasource.database.FoodSafetyDatabase
import com.zhengdianfang.foodsafety.main.datasource.database.NavigationMenuDao
import dagger.Module
import dagger.Provides


@Module
class DaoModule {

    @Provides
    fun provideNavigationMenuDao(): NavigationMenuDao {
        return FoodSafetyDatabase.getInstance(FoodSafetyApplication.INSTANCE)?.navigationMenuDao()!!
    }
}