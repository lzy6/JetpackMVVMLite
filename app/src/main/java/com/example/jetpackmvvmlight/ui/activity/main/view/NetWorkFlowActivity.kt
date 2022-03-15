package com.example.jetpackmvvmlight.ui.activity.main.view

import androidx.activity.viewModels
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.databinding.ActivityNetworkBinding
import com.example.jetpackmvvmlight.ui.activity.main.viewmodel.NetWorkFlowViewModel

class NetWorkFlowActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityNetworkBinding::inflate)
    private val viewModel by viewModels<NetWorkFlowViewModel>()

    override fun initData() {
        init()
        initClick()
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
    }


    override fun request() {
        viewModel.run {
            /**
             * 单个实体回调
             */
            observe(singEntityLiveData) {}.toState(::dialogState)
        }
    }
}