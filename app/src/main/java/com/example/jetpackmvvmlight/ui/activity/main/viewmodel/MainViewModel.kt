package com.example.jetpackmvvmlight.ui.activity.main.viewmodel

import com.example.jetpackmvvmlight.app.base.BaseViewModel
import com.example.jetpackmvvmlight.app.base.StateLiveData
import com.example.jetpackmvvmlight.app.utils.RetryUtil
import com.example.jetpackmvvmlight.entity.Page
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.entity.User
import java.net.SocketTimeoutException

class MainViewModel : BaseViewModel() {

    val pageEntityLiveData = StateLiveData<Page<PageUser>>()
    val singEntityLiveData = StateLiveData<User>()
    val mayNullLiveData = StateLiveData<Any?>().allowNull(true)

    /**
     * 单个实体调用
     */
    fun singEntity() = launchUI(
        response = { mApi.singEntity().data!! },
        liveData = singEntityLiveData
    )

    /**
     * 可以为null时调用
     */
    fun mayNull() = launchUI(
        response = { mApi.mayNull().data },
        liveData = mayNullLiveData
    )

    /**
     * 分页调用
     */
    fun pageUser(pageNum: Int) = launchUIPage(
        pageNum,
        response = { mApi.pageEntity(pageNum).data!! },
        liveData = pageEntityLiveData
    )
}