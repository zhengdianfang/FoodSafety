package com.zhengdianfang.foodsafety.profile.fragments.register

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zhengdianfang.foodsafety.R
import com.zhengdianfang.foodsafety.profile.fragments.LoginFragment
import com.zhengdianfang.miracleframework.BaseFragment
import com.zhengdianfang.miracleframework.fragment.ISupportFragment
import kotlinx.android.synthetic.main.enterprise_register_fragment.*
import org.jetbrains.anko.support.v4.toast

class EnterpriseRegisterFragment: BaseFragment() {

    private  lateinit var viewModel: EnterpriseRegisterViewModel

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var toast: Toast? = null

    companion object {
        fun newInstance() = EnterpriseRegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.enterprise_register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EnterpriseRegisterViewModel::class.java)
        bindLiveDatas()
        bindActions()
    }

    private fun bindLiveDatas() {
        viewModel.userLiveData.observe(this, Observer { user ->
            if (user != null) {
                toast = toast(R.string.register_success_toast)
            }
        })
    }

    private fun bindActions() {
        nextStepButton.setOnClickListener {
            try {
                viewModel.register(userNameEdit.text.toString(), passwordEdit.text.toString(), confirmPasswordEdit.text.toString())
            } catch (e: IllegalStateException) {
                toast = toast(e.message ?: "")
            }
        }

        existAccountView.setOnClickListener {
            popTo(LoginFragment::class.java, false)
        }
    }
}