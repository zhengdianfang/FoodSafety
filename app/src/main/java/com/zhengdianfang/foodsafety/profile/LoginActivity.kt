package com.zhengdianfang.foodsafety.profile

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhengdianfang.foodsafety.profile.fragments.LoginFragment
import com.zhengdianfang.miracleframework.BaseActivity

@Route(path = "/login/index")
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRootFragment(android.R.id.content, LoginFragment.newInstance())
    }

}
