package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.R
import kotlinx.android.synthetic.main.activity_announcement.*

class AnnouncementActivity : BaseActivity(R.layout.activity_announcement) {

    override fun initData() {
        init()
        initClick()
    }

    /**
     * 初始化点击事件
     */
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