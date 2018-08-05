package com.zhengdianfang.foodsafety.profile.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.zhengdianfang.foodsafety.FoodSafetyApplication
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.miracleframework.utils.StringExtension

class FindPasswordViewModel(application: Application) : AndroidViewModel(application) {

    val resetPasswordSucceed = MutableLiveData<Boolean>()

    @Throws(IllegalStateException::class)
    fun resetPassword(username: String,
                      smsCode: String,
                      newPassword: String,
                      confirmPassword: String) {

        resetPasswordSucceed.value = false

        check(!username.isNullOrEmpty()) {
                    getApplication<FoodSafetyApplication>().resources.getString(R.string.email_hint)
        }
        check(StringExtension.isValidateEmail(username)) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.email_format_toast)
        }
        check(!smsCode.isNullOrEmpty()) {
                    getApplication<FoodSafetyApplication>().resources.getString(R.string.sms_code_hint)
        }
        check(!newPassword.isNullOrEmpty()) {
                    getApplication<FoodSafetyApplication>().resources.getString(R.string.password_hint)
        }
        check(!confirmPassword.isNullOrEmpty()) {
                    getApplication<FoodSafetyApplication>().resources.getString(R.string.confirm_password_hint)
        }
        check(newPassword == confirmPassword) {
            getApplication<FoodSafetyApplication>().resources.getString(R.string.password_eqaul_toast)
        }

        resetPasswordSucceed.postValue(true)
    }

}
