package com.zhengdianfang.foodsafety.profile

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhengdianfang.foodsafety.profile.fragments.register.RegisterFragment
import com.zhengdianfang.miracleframework.BaseActivity

@Route(path = "/profile/register")
class RegisterActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRootFragment(android.R.id.content, RegisterFragment.newInstance())
    }
}