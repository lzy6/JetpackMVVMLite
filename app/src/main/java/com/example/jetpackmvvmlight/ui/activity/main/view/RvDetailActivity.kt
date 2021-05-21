package com.example.jetpackmvvmlight.ui.activity.main.view

import androidx.core.view.ViewCompat
import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.R
import kotlinx.android.synthetic.main.activity_rv_detail.*

class RvDetailActivity : BaseActivity(R.layout.activity_rv_detail) {

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

        ViewCompat.setTransitionName(iv_img, IMAGE)
        ViewCompat.setTransitionName(tv_title, TITLE)
    }

}