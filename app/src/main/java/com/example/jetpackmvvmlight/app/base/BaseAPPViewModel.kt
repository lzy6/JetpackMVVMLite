package com.example.jetpackmvvmlight.app.base

import com.example.commonlib.base.BaseViewModel
import com.example.commonlib.base.RetrofitServiceManager
import com.example.jetpackmvvmlight.service.APPService

open class BaseAPPViewModel : BaseViewModel() {
    /**
     * 接口service
     */
    protected val mApi: APPService by lazy {
        return@lazy RetrofitServiceManager.instance.create(APPService::class.java)
    }

}