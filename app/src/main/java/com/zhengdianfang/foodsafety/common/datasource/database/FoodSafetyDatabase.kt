package com.zhengdianfang.foodsafety.common.datasource.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.datasource.UserDao

@Database(entities = [(User::class)], version = 1)
abstract class FoodSafetyDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        private var  INSTANCE: FoodSafetyDatabase? = null
        private val lockObject = Any()
        fun getInstance(context: Context): FoodSafetyDatabase? {
            synchronized(lockObject, {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FoodSafetyDatabase::class.java, "FoodSafety.db")
                            .build()
                }
                return INSTANCE
            })
        }
    }

}