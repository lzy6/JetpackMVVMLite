package com.example.jetpackmvvmlight.ui.activity.main.view

import android.os.Bundle
import com.example.commonlib.*
import com.example.commonlib.app.Constant
import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.dataMain
import com.example.jetpackmvvmlight.databinding.ActivityMainBinding
import com.example.jetpackmvvmlight.ui.adapter.MainAdapter

class MainActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityMainBinding::inflate)
    private val adapter by lazy { MainAdapter() }

    override fun initData() {
        init()
        initRv()
        initClick()
    }

    override fun request() {

    }

    private fun init() {
        statusColor(this, true)
        setSupportActionBar(viewBind.toolbar)
    }

    private fun initRv() {
        viewBind.rvList.adapter = adapter
        adapter.setNewInstance(dataMain())
    }

    private fun initClick() {
        viewBind.run {
            llFrame.onClick {
                val bundle = Bundle().apply {
                    putString(Constant.URL, Constant.FRAME_URL)
                }
                startKtxActivity<WebViewActivity>(extra = bundle)
            }
            llFrameCompose.onClick {
                toast("正在开发")
            }
            llProjectCompose.onClick { toast("正在开发") }
        }
    }

}