package com.example.settinglib.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.app.RoutePath
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.onClick
import com.example.commonlib.viewModels
import com.example.settinglib.R
import com.example.settinglib.ui.viewmodel.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*

@Route(path = RoutePath.SettingActivity)
class SettingActivity : BaseActivity(R.layout.activity_setting) {

    private val mModel by lazy { viewModels<SettingViewModel>(this) }

    override fun initData() {
        init()
        initClick()
    }

    private fun initClick() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    /**
     * 初始化
     */
    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}