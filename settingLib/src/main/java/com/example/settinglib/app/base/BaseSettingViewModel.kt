package com.example.settinglib.app.base

import com.example.commonlib.base.BaseViewModel
import com.example.commonlib.base.RetrofitServiceManager
import com.example.settinglib.service.SettingService

open class BaseSettingViewModel : BaseViewModel() {
    /**
     * 接口service
     */
    protected val api by lazy { RetrofitServiceManager.create(SettingService::class.java) }

}