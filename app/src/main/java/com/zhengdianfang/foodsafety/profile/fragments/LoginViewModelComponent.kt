package com.zhengdianfang.foodsafety.profile.fragments

import dagger.Component

@Component
interface LoginViewModelComponent {
    fun inject(loginViewModel: LoginViewModel)
}