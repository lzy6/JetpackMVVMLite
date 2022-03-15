package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.base.BaseActivity
import com.example.commonlib.radiusAndShadow
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.databinding.ActivityRadiusLayoutBinding

class RadiusLayoutActivity :BaseActivity(){

    private val viewBind by viewBinding(ActivityRadiusLayoutBinding::inflate)

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    private fun initClick() {
        viewBind.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        viewBind.flRadiusShadow.radiusAndShadow(this,5f,5,0.5f)
    }

}