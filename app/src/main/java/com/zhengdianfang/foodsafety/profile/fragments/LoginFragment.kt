package com.zhengdianfang.foodsafety.profile.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.common.model.User
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import org.jetbrains.anko.support.v4.toast

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var toast: Toast? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        initActions()

    }

    private fun initActions() {
        viewModel.userLiveData.observe(this, Observer<User> { loginUser ->
            if (null != loginUser) {
                this.toast = toast(R.string.login_success_toast)
            }
        })
        loginButton.setOnClickListener {
            try {
                viewModel.login(emailEdit.text.toString(), passwordEdit.text.toString())
            } catch (e: IllegalStateException) {
                this.toast = toast(e.message ?: "")
            }
        }

        findPasswordView.setOnClickListener {
           start(FindPasswordFragment.newInstance())
        }
    }

}
