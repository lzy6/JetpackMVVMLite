package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.addDivider
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.dataHistoryComponents
import com.example.jetpackmvvmlight.databinding.ActivityHistoryComponentsBinding
import com.example.jetpackmvvmlight.ui.adapter.DescribeAdapter

class HistoryComponentsActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityHistoryComponentsBinding::inflate)
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

    private fun initRv(){
        viewBind.rvList.adapter=adapter
        viewBind.rvList.addDivider(5f,
            showFirstDivider = false,
            showLastDivider = false,
            showSideDividers = false
        )
        adapter.setNewInstance(dataHistoryComponents())
    }
}