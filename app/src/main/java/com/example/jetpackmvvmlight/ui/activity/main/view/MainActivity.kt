package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.base.BaseActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.dataMain
import com.example.jetpackmvvmlight.databinding.ActivityMainBinding
import com.example.jetpackmvvmlight.ui.adapter.MainAdapter

class MainActivity : BaseActivity(){

    private val viewBind by viewBinding(ActivityMainBinding::inflate)
    private val adapter by lazy { MainAdapter() }

    override fun initData() {
        init()
        initRv()
    }

    override fun request() {

    }

    private fun init() {
        statusColor(this,true)
        setSupportActionBar(viewBind.toolbar)
    }

    private fun initRv(){
        viewBind.rvList.adapter=adapter
        adapter.setNewInstance(dataMain())
    }

}