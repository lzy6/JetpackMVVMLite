package com.example.jetpackmvvmlight.ui.activity.main.view

import androidx.activity.viewModels
import com.example.commonlib.*
import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.ui.activity.main.viewmodel.NetWorkViewModel
import com.example.jetpackmvvmlight.ui.adapter.UserAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_network.*

class NetWorkActivity : BaseActivity(R.layout.activity_network), OnRefreshLoadMoreListener {
    private val viewModel by viewModels<NetWorkViewModel>()
    private val adapter by lazy { UserAdapter() }
    private var page = 1

    override fun initData() {
        init()
        initListener()
        request()
    }

    private fun initListener() {
        refresh.setOnRefreshLoadMoreListener(this)
    }

    /**
     * 数据回传
     */
    private fun request() {
        viewModel.run {
            /**
             * 分页回调
             */
            pageEntityLiveData.observeInActivity(this@NetWorkActivity) { setData(it.list) }
            pageEntityLiveData.state.observeInActivity(this@NetWorkActivity) { state ->
                apiState(state,
                    complete = { refresh.complete() },
                    stateLayout = { adapter.setEmptyView(it) },
                    hasMore = { refresh.finishMore(it) }
                )
            }

            /**
             * 单个实体回调
             */
            singEntityLiveData.observeInActivity(this@NetWorkActivity) {

            }
            singEntityLiveData.state.observeInActivity(this@NetWorkActivity) {
                apiState(
                    it,
                    before = { showLoading() },
                    complete = { dismissLoading() })
            }

            /**
             * 可以为null时回调
             */
            mayNullLiveData.observeInActivity(this@NetWorkActivity) {

            }
            mayNullLiveData.state.observeInActivity(this@NetWorkActivity) {
                apiState(
                    it,
                    before = { showLoading() },
                    complete = { dismissLoading() })
            }

            /**
             * 缓存方式回调
             */
            cacheEntityLiveData.observeInActivity(this@NetWorkActivity) {
                //这里会执行两次回调数据，缓存数据+网络数据
            }
            cacheEntityLiveData.state.observeInActivity(this@NetWorkActivity) {
                apiState(
                    it,
                    before = { showLoading() },
                    complete = { dismissLoading() })
            }

            //初始调用接口
            singEntity()
            mayNull()
            cacheData()
        }
    }

    /**
     * 装填数据
     */
    private fun setData(data: ArrayList<PageUser>) {
        adapter.setPageData(page, data)
        page++
    }

    private fun init() {
        initNoViewStatusBar(true)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.pageUser(page)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.pageUser(page)
    }

}