package com.zhengdianfang.foodsafety.profile.repository

import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.datasource.api.UserApi
import com.zhengdianfang.foodsafety.profile.datasource.database.UserDao
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi,
                                         private val userDao: UserDao){

    fun login(username: String, password: String, receiverLoginUser: (userLiveData: User?) -> Unit) {
        doAsync {
            val userResponse = userApi.login(username, password)
            val execute = userResponse.execute()
            if (execute.isSuccessful) {
                var user = execute.body()
                if (user != null) {
                    userDao.save(user)
                    user = userDao.getUser(user.id)
                    uiThread {
                        receiverLoginUser(user)
                    }
                }
            }
        }
    }
}