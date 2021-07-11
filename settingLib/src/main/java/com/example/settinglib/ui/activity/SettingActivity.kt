package com.example.settinglib.ui.activity

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.commonlib.app.RoutePath
import com.example.commonlib.base.BaseActivity
import com.example.settinglib.R
import com.example.settinglib.databinding.ActivitySettingBinding
import com.example.settinglib.ui.viewmodel.SettingViewModel

@Route(path = RoutePath.SettingActivity)
class SettingActivity : BaseActivity(R.layout.activity_setting) {

//    private val viewBind by viewBinding(ActivitySettingBinding::inflate)
    private val viewModel by viewModels<SettingViewModel>()

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    private fun initClick() {
//        viewBind.toolbar.setNavigationOnClickListener {
//            onBackPressed()
//        }
    }

    /**
     * 初始化
     */
    private fun init() {
//        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}