package com.example.jetpackmvvmlight

import android.app.Application
import android.os.Build
import android.os.StrictMode
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.tencent.mmkv.MMKV

class APP : Application() {

    companion object {
        lateinit var instance: Application
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                ClassicsHeader(
                    context
                )
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(
                    context
                ).setSpinnerStyle(SpinnerStyle.Translate)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initMMKV()
        initDetectFileUriExposure()
    }

    private fun initMMKV() {
        MMKV.initialize(this)
    }

    //解决android7.0以上拍照崩溃问题
    private fun initDetectFileUriExposure() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure()
        }
    }

}
