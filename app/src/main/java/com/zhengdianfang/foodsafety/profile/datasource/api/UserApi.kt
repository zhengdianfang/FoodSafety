package com.zhengdianfang.foodsafety.profile.datasource.api

import com.zhengdianfang.foodsafety.common.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Call<User>
}