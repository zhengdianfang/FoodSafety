package com.zhengdianfang.foodsafety.main.dagger

import com.zhengdianfang.foodsafety.common.dagger.DaoModule
import com.zhengdianfang.foodsafety.main.fragments.MainLeftMenusViewModel
import dagger.Component


@Component(modules = [(DaoModule::class)])
interface MainLeftMenusViewModelComponent {
    fun inject(mainLeftMenusViewModel: MainLeftMenusViewModel)
}