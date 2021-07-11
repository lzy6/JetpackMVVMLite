package com.example.jetpackmvvmlight.ui.activity.main.view

import androidx.activity.viewModels
import com.example.commonlib.*
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.entity.Page
import com.example.commonlib.entity.State
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ActivityNetworkBinding
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.ui.activity.main.viewmodel.NetWorkViewModel
import com.example.jetpackmvvmlight.ui.adapter.UserAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class NetWorkActivity : BaseActivity(R.layout.activity_network), OnRefreshLoadMoreListener {

    private val viewBind by viewBinding(ActivityNetworkBinding::inflate)
    private val viewModel by viewModels<NetWorkViewModel>()
    private val adapter by lazy { UserAdapter() }
    private var page = 1

    override fun initData() {
        init()
        initListener()
    }

    private fun initListener() {
        viewBind.refresh.setOnRefreshLoadMoreListener(this)
    }


    /**
     * 数据回传
     */
    override fun request() {
        viewModel.run {
            /**
             * 分页回调
             */
            observe(pageEntityLiveData, ::pageEntityResult).toState(::pageEntityState)
            /**
             * 单个实体回调
             */
            observe(singEntityLiveData) {}.toState(::dialogState)
            /**
             * 可以为null时回调
             */
            observe(mayNullLiveData) {}.toState(::dialogState)
            /**
             * 缓存方式回调
             */
            observe(singEntityLiveData) {}.toState(::dialogState)
        }
    }

    private fun init() {
        initNoViewStatusBar(true)
    }

    /**
     * 分页回调-state
     */
    private fun pageEntityState(state: State) {
        apiState(state,
            complete = { viewBind.refresh.complete() },
            stateLayout = { adapter.setEmptyView(it) },
            hasMore = { viewBind.refresh.finishMore(it) }
        )
    }

    /**
     * 装填数据
     */
    private fun pageEntityResult(data: Page<PageUser>) {
        adapter.setPageData(page, data.list)
        page++
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.pageUser(page)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.pageUser(page)
    }

}