package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.commonlib.addDivider
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.dataComponents
import com.example.jetpackmvvmlight.databinding.ActivityComponentsBinding
import com.example.jetpackmvvmlight.ui.adapter.ComponentsAdapter

class ComponentsActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityComponentsBinding::inflate)
    private val adapter by lazy { ComponentsAdapter() }

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
        viewBind.rvList.addDivider(1f)
        adapter.setNewInstance(dataComponents())
    }
}