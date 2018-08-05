package com.zhengdianfang.foodsafety.profile.repository

import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.datasource.api.UserApi
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi,
                                         private val userDao: UserDao){

    fun login(username: String, password: String): User? {
            var user: User? = null
            val userResponse = userApi.login(username, password)
            val execute = userResponse.execute()
            if (execute.isSuccessful) {
                user = execute.body()
                if (user != null) {
                    userDao.save(user)
                }
            }
        return user
    }


    fun resetPassword(username: String, smsCode: String, newPassword: String,
                      confirmPassword: String) {

    }
}