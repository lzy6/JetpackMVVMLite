package com.example.jetpackmvvmlight.ui.activity.main.view

import android.os.Bundle
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.startKtxActivity
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(null)
    }

    override fun initData() {
        startKtxActivity<MainActivity>()
        finish()
    }

    override fun request() {

    }

}