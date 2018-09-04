package com.zhengdianfang.foodsafety.setting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhengdianfang.foodsafety.setting.fragments.SettingFragment
import com.zhengdianfang.miracleframework.BaseActivity

@Route(path = "/setting/index")
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRootFragment(android.R.id.content, SettingFragment.newInstance())
    }
}
