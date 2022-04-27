package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.base.BaseActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.databinding.ActivityBriefIntroductionBinding

class BriefIntroductionActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityBriefIntroductionBinding::inflate)

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
        statusColor(this, true)
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}