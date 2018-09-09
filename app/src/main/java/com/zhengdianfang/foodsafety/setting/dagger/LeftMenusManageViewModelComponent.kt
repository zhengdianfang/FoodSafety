package com.zhengdianfang.foodsafety.setting.dagger

import com.zhengdianfang.foodsafety.common.dagger.DaoModule
import com.zhengdianfang.foodsafety.setting.fragments.LeftMenuManageViewModel
import dagger.Component

@Component(modules = [(DaoModule::class)])
interface LeftMenusManageViewModelComponent  {
    fun inject(leftMenuManageViewModel: LeftMenuManageViewModel)
}
