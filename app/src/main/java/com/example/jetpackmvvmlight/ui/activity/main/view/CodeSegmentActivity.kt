package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.addDivider
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.startKtxActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.dataCodeSegment
import com.example.jetpackmvvmlight.databinding.ActivityCodeSegmentBinding
import com.example.jetpackmvvmlight.ui.adapter.DescribeAdapter

class CodeSegmentActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityCodeSegmentBinding::inflate)
    private val adapter by lazy { DescribeAdapter() }

    override fun initData() {
        init()
        initClick()
        initRv()
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

    private fun initRv() {
        viewBind.rvList.adapter = adapter
        viewBind.rvList.addDivider(5f,
            showFirstDivider = false,
            showLastDivider = false,
            showSideDividers = false
        )
        adapter.setNewInstance(dataCodeSegment())
    }

}