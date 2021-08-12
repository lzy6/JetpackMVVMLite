package com.example.commonlib

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.tencent.mmkv.MMKV
import java.io.File

/**
 * Created by luyao
 * on 2019/6/12 10:53
 */

val Context.versionName: String
    get() = packageManager.getPackageInfo(packageName, 0).versionName

val Context.versionCode: Long
    get() = with(packageManager.getPackageInfo(packageName, 0)) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) longVersionCode else versionCode.toLong()
    }

/**
 * Get app signature by [packageName]
 */
fun Context.getAppSignature(packageName: String = this.packageName): ByteArray? {
    val packageInfo: PackageInfo =
        packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
    val signatures = packageInfo.signatures
    return signatures[0].toByteArray()
}

/**
 * Whether the application is installed
 */
fun Context.isPackageInstalled(pkgName: String): Boolean {
    return try {
        packageManager.getPackageInfo(pkgName, 0)
        true
    } catch (e: Exception) {
        false
    }
}

/**
 * 初始化SmartRefresh
 */
fun initSmartRefresh(){
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

/**
 * 初始化MMKV
 */
fun Application.initMMKV() {
    MMKV.initialize(this)
}

/**
 * 初始化ARouter
 */
fun Application.initARouter() {
    if (BuildConfig.DEBUG) {
        ARouter.openLog()
        ARouter.openDebug()
    }
    ARouter.init(this)
}


