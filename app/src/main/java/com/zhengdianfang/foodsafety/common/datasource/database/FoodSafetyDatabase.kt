package com.zhengdianfang.foodsafety.common.datasource.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.main.datasource.database.NavigationMenuDao
import com.zhengdianfang.foodsafety.main.model.MainMenuItem
import com.zhengdianfang.foodsafety.main.model.SubMenuItem
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao

@Database(
        entities = [
            User::class,
            MainMenuItem::class,
            SubMenuItem::class
        ],
        version = 1,
        exportSchema = false
)
abstract class FoodSafetyDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun navigationMenuDao(): NavigationMenuDao

    companion object {
        private var  INSTANCE: FoodSafetyDatabase? = null
        private val lockObject = Any()
        fun getInstance(context: Context): FoodSafetyDatabase? {
            synchronized(lockObject) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, FoodSafetyDatabase::class.java, "FoodSafety.db")
                            .build()
                }
                return INSTANCE
            }
        }
    }

}