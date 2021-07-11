package com.example.jetpackmvvmlight.ui.activity.main.view

import androidx.core.view.ViewCompat
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ActivityRvDetailBinding

class RvDetailActivity : BaseActivity(R.layout.activity_rv_detail) {

    private val viewBind by viewBinding(ActivityRvDetailBinding::inflate)

    companion object {
        const val IMAGE = "image"
        const val TITLE = "title"
    }

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    private fun initClick(){
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

        ViewCompat.setTransitionName(viewBind.ivImg, IMAGE)
        ViewCompat.setTransitionName(viewBind.tvTitle, TITLE)
    }

}