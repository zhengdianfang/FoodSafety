package com.zhengdianfang.foodsafety.profile.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.foodsafety.profile.dagger.DaggerLoginViewModelComponent
import com.zhengdianfang.foodsafety.profile.repository.UserRepository
import com.zhengdianfang.miracleframework.utils.StringExtension
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var userLiveData = MutableLiveData<User>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        DaggerLoginViewModelComponent.create().inject(this)
    }

    fun login(email: String, password: String) {
        check(!email.isNullOrBlank()) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.email_hint)
        }
        check(StringExtension.isValidateEmail(email)) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.email_format_toast)
        }
        check(!password.isNullOrBlank()) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.password_hint)
        }
        // TODO integrate api
        userLiveData.postValue(User(1, "zdf", "zdf"))
    }
}
