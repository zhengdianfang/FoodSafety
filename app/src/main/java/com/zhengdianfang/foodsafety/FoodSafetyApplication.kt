package com.zhengdianfang.foodsafety

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class FoodSafetyApplication: Application() {

    companion object {
        lateinit var INSTANCE: FoodSafetyApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initARouter()
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }
}