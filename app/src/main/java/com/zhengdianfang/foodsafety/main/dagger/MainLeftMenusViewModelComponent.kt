package com.zhengdianfang.foodsafety.main.dagger

import com.zhengdianfang.foodsafety.main.fragments.MainLeftMenusViewModel
import dagger.Component


@Component(modules = [(MainLeftMenusModule::class)])
interface MainLeftMenusViewModelComponent {
    fun inject(mainLeftMenusViewModel: MainLeftMenusViewModel)
}