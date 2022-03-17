package com.example.commonlib

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.app.Constant
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.tencent.mmkv.MMKV

open class CommonAPP : Application() {

    companion object {
        lateinit var instance: Application

        init {
            initSmartRefresh()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initARouter()
        initMMKV()
        toggleNightMode()
    }

    private fun toggleNightMode() {
        if (mmkv().getInt(Constant.NIGHT_MODE, Constant.NO_NIGHT_MODE) == Constant.NO_NIGHT_MODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}