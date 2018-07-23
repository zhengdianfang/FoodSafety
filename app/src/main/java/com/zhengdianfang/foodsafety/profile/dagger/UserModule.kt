package com.zhengdianfang.foodsafety.profile.dagger

import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.common.datasource.api.ApiClient
import com.zhengdianfang.foodsafety.common.datasource.database.FoodSafetyDatabase
import com.zhengdianfang.foodsafety.profile.datasource.api.UserApi
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserApi(): UserApi  {
        return ApiClient.CLIENT.create(UserApi::class.java)
    }

    @Provides
    fun provideUserDao(): UserDao {
        return FoodSafetyDatabase.getInstance(FoodSafetyApplication.INSTANCE)?.userDao()!!
    }
}