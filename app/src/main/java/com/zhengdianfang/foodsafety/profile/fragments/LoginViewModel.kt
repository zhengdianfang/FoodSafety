package com.zhengdianfang.foodsafety.profile.fragments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.repository.UserRepository
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    var userLiveData = MutableLiveData<User>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        DaggerLoginViewModelComponent.create().inject(this)
    }

    fun login(context: Context, username: String, password: String) {
        userRepository.login(context, username, password, {user ->
            this.userLiveData.postValue(user)
        })
    }
}
