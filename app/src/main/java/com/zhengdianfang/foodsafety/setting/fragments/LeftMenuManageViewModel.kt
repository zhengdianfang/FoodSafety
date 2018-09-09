package com.zhengdianfang.foodsafety.setting.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.zhengdianfang.foodsafety.main.repository.NavigationMenuRepository
import com.zhengdianfang.foodsafety.setting.dagger.DaggerLeftMenusManageViewModelComponent
import javax.inject.Inject

class LeftMenuManageViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var navigationMenuRepository: NavigationMenuRepository

    init {
        DaggerLeftMenusManageViewModelComponent.create().inject(this)
    }

}