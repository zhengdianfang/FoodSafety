package com.zhengdianfang.foodsafety.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhengdianfang.foodsafety.main.fragments.MainFragment
import com.zhengdianfang.miracleframework.BaseActivity

@Route(path = "/main/index")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRootFragment(android.R.id.content, MainFragment.newInstance())
    }

}
