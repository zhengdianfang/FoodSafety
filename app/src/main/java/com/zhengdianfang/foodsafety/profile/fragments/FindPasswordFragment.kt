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
import com.zhengdianfang.miracleframework.BaseFragment
import kotlinx.android.synthetic.main.find_password_fragment.*
import kotlinx.android.synthetic.main.navigation_bar_dark_layout.*
import org.jetbrains.anko.support.v4.toast

class FindPasswordFragment : BaseFragment() {

    companion object {
        fun newInstance() = FindPasswordFragment()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var toast: Toast? = null

    private lateinit var viewModel: FindPasswordViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.find_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigationTitleView.setText(R.string.find_password_title)

        viewModel = ViewModelProviders.of(this).get(FindPasswordViewModel::class.java)

        viewModel.resetPasswordSucceed.observe(this, Observer<Boolean> { isSucceed ->
           if (isSucceed!!) {
              toast = toast(R.string.reset_password_success_toast)
           }
        })

        resetPasswordButton.setOnClickListener {
            try {
                viewModel.resetPassword(userNameEdit.text.toString(), smsCodeEdit.text.toString(),
                        newPasswordEdit.text.toString(), confirmPasswordEdit.text.toString())
            }catch (e: IllegalStateException) {
               toast = toast(e.message ?: "")
            }
        }
    }

}
