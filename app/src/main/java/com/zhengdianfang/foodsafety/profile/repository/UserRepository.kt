package com.zhengdianfang.foodsafety.profile.repository

import android.content.Context
import com.zhengdianfang.foodsafety.common.Constant.LOG_TAG
import com.zhengdianfang.foodsafety.common.datasource.api.ApiClient
import com.zhengdianfang.foodsafety.common.datasource.database.FoodSafetyDatabase
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.api.ProfileApi
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class UserRepository @Inject constructor(){
    private val logger = AnkoLogger(LOG_TAG)


    fun login(context: Context, username: String, password: String, receiverLoginUser: (userLiveData: User?) -> Unit) {
        doAsync {
            val userResponse = ApiClient.CLIENT.create(ProfileApi::class.java).login(username, password)
            logger.debug("response : $userResponse")
            val execute = userResponse.execute()
            if (execute.isSuccessful) {
                val userDao = FoodSafetyDatabase.getInstance(context)?.userDao()
                var user = execute.body()
                if (user != null) {
                    userDao?.save(user)
                    user = userDao?.getUser(user.id)
                    uiThread {
                        receiverLoginUser(user)
                    }
                }
            }
        }
    }
}