package com.example.jetpackmvvmlight.ui.activity.main.view

import com.example.jetpackmvvmlight.*
import com.example.jetpackmvvmlight.app.base.BaseActivity
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.ui.activity.main.viewmodel.NetWorkViewModel
import com.example.jetpackmvvmlight.ui.adapter.UserAdapter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_network.*

class NetWorkActivity :BaseActivity(R.layout.activity_network), OnRefreshLoadMoreListener {
    private val mModel by lazy { viewModels<NetWorkViewModel>(this) }
    private val mAdapter by lazy { UserAdapter() }
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
        mModel.run {
            pageEntityLiveData.observeInActivity(this@NetWorkActivity) { setData(it.list) }
            pageEntityLiveData.state.observeInActivity(this@NetWorkActivity) { state ->
                apiState(state,
                    complete = { refresh.complete() },
                    stateLayout = { mAdapter.setEmptyView(it) },
                    hasMore = { refresh.finishMore(it) }
                )
            }

            singEntityLiveData.observeInActivity(this@NetWorkActivity) {

            }
            singEntityLiveData.state.observeInActivity(this@NetWorkActivity) {
                apiState(
                    it,
                    before = { showLoading() },
                    complete = { dismissLoading() })
            }

            mayNullLiveData.observeInActivity(this@NetWorkActivity) {

            }
            mayNullLiveData.state.observeInActivity(this@NetWorkActivity) {
                apiState(
                    it,
                    before = { showLoading() },
                    complete = { dismissLoading() })
            }

            //初始调用接口
            singEntity()
            mayNull()
        }
    }

    /**
     * 装填数据
     */
    private fun setData(data: ArrayList<PageUser>) {
        mAdapter.setPageData(page, data)
        page++
    }

    private fun init() {
        initNoViewStatusBar(true)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mModel.pageUser(page)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mModel.pageUser(page)
    }

}