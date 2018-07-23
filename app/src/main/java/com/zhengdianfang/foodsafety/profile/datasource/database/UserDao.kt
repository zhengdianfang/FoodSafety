package com.zhengdianfang.foodsafety.profile.datasource.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.zhengdianfang.foodsafety.common.model.User

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun save(user: User)

    @Query("select * from user where id=:id")
    fun getUser(id: Int): User?
}