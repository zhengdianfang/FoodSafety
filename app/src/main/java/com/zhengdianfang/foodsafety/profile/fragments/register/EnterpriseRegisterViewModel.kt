package com.zhengdianfang.foodsafety.profile.fragments.register

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.miracleframework.utils.StringExtension

class EnterpriseRegisterViewModel(application: Application) : AndroidViewModel(application) {

    val userLiveData = MutableLiveData<User>()

    fun register(username: String, password: String, confirmPassword: String) {
        check(!username.isNullOrBlank()) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.email_or_phone_toast)
        }

        check(StringExtension.isValidateEmail(username) || StringExtension.isValidatePhone(username)) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.email_or_phone_toast)
        }

        check(!password.isNullOrBlank()) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.password_hint)
        }

        check(!confirmPassword.isNullOrBlank()) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.confirm_password_hint)
        }

        check(confirmPassword == password) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.password_eqaul_toast)
        }

        // TODO fetch register api
        userLiveData.postValue(User(0, "zdf", "zdf"))
    }

}
