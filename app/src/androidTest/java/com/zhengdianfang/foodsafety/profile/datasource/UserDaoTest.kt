package com.zhengdianfang.foodsafety.profile.datasource

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.zhengdianfang.foodsafety.common.datasource.database.FoodSafetyDatabase
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var foodSafetyDatabase: FoodSafetyDatabase
    private lateinit var userDao: UserDao

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        foodSafetyDatabase = Room.inMemoryDatabaseBuilder(context, FoodSafetyDatabase::class.java).build()
        userDao = foodSafetyDatabase.userDao()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        foodSafetyDatabase.close()
    }

    @Test
    fun testSaveAndQuery() {
        val user = User(1, "xiangxiang", "xx")
        userDao.save(user)
        val query = userDao.getUser(1)
        assertThat(query, equalTo(user))
    }
}