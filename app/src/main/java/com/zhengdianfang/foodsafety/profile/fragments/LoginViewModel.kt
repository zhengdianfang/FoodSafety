package com.zhengdianfang.foodsafety.profile.fragments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.dagger.DaggerLoginViewModelComponent
import com.zhengdianfang.foodsafety.profile.repository.UserRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    var userLiveData = MutableLiveData<User>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        DaggerLoginViewModelComponent.create().inject(this)
    }

    fun login(username: String, password: String) {
        doAsync {
            val user = userRepository.login(username, password)
            uiThread {
                userLiveData.postValue(user)
            }
        }
    }
}
