package com.example.jetpackmvvmlight.ui.activity.main.viewmodel

import com.example.commonlib.app.Constant
import com.example.commonlib.base.CacheManager
import com.example.commonlib.base.StateLiveData
import com.example.commonlib.entity.Page
import com.example.jetpackmvvmlight.app.base.BaseAPPViewModel
import com.example.jetpackmvvmlight.entity.CacheEntity
import com.example.jetpackmvvmlight.entity.PageUser
import com.example.jetpackmvvmlight.entity.User

class NetWorkViewModel : BaseAPPViewModel() {

    val pageEntityLiveData = StateLiveData<Page<PageUser>>()
    val singEntityLiveData = StateLiveData<User>()
    val mayNullLiveData = StateLiveData<Any?>().allowNull(true)
    val cacheEntityLiveData = StateLiveData<ArrayList<CacheEntity>>()

    /**
     * 单个实体调用
     */
    fun singEntity() = launchUI(
        response = { api.singEntity().data!! },
        liveData = singEntityLiveData
    )

    /**
     * 可以为null时调用
     */
    fun mayNull() = launchUI(
        response = { api.mayNull().data },
        liveData = mayNullLiveData
    )

    /**
     * 分页调用
     */
    fun pageUser(pageNum: Int) = launchUIPage(
        pageNum,
        response = { api.pageEntity(pageNum).data!! },
        liveData = pageEntityLiveData
    )

    /**
     * 缓存方式调用
     */
    fun cacheData() = launchUIPageCache(
        cache = {
            CacheManager.getInstance().getCacheList(
                Constant.CACHE,
                CacheEntity::class.java
            )
        },
        response = { api.cacheEntity().data!! },
        liveData = cacheEntityLiveData
    )
}