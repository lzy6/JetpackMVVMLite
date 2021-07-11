package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.base.BaseActivity
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ActivityAnnouncementBinding

class AnnouncementActivity : BaseActivity(R.layout.activity_announcement) {

    private val viewBind by viewBinding(ActivityAnnouncementBinding::inflate)

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    /**
     * 初始化点击事件
     */
    private fun initClick() {
        viewBind.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    /**
     * 初始化
     */
    private fun init() {
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

}