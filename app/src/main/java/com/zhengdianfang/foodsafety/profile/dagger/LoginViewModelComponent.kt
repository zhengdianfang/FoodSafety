package com.zhengdianfang.foodsafety.profile.dagger

import com.zhengdianfang.foodsafety.profile.fragments.LoginViewModel
import dagger.Component

@Component(modules = [(UserModule::class)])
interface LoginViewModelComponent {
    fun inject(loginViewModel: LoginViewModel)
}