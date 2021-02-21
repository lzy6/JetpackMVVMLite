package com.example.commonlib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.commonlib.app.Constant
import com.example.commonlib.utils.UtilToast
import com.google.android.material.snackbar.Snackbar
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.tencent.mmkv.MMKV
import java.io.File


/**
 * 吐司
 */
fun toast(toast: String) {
    UtilToast.showToast(CommonAPP.instance, toast)
}

fun Context.snackBarToast(view: View, strId: Int) {
    Snackbar.make(view, getString(strId), Snackbar.LENGTH_SHORT).show()
}

/**
 * List<*>
 * 判断空或没数据
 */
fun List<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * 任意类型
 * 判断是空
 */
fun Any?.isNull(): Boolean {
    return this == null
}

/**
 * 任意类型
 * 判断不是空
 */
fun Any?.isNotNull(): Boolean {
    return this != null
}

/**
 * String
 * 判断空或没数据
 */

fun String?.isNullorEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * 根据布局id获取view
 */
fun getLayoutView(context: Context, layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, null, false)
}

/**
 * 根据bundle+flags跳转activity
 */
fun startActivity(context: Context, clazz: Class<*>, params: Bundle, flags: Int) {
    val intent = Intent(context, clazz)
    intent.putExtras(params)
    intent.flags = flags
    context.startActivity(intent)
}

/**
 * 直接跳转activity
 */
fun startActivity(context: Context, clazz: Class<*>) {
    context.startActivity(Intent(context, clazz))
}

/**
 * 根据flags跳转activity
 */
fun startActivity(context: Context, clazz: Class<*>, flags: Int) {
    val intent = Intent(context, clazz)
    intent.flags = flags
    context.startActivity(intent)
}

/**
 * 根据bundle跳转activity
 */
fun startActivity(context: Context, clazz: Class<*>, params: Bundle) {
    val intent = Intent(context, clazz)
    intent.putExtras(params)
    context.startActivity(intent)
}

/**
 * 打印日志
 */
fun log(tag: String, log: Any) {
    Log.d(tag, "=====>$log")
}

/**
 * test数据
 */
fun getData(): ArrayList<String> {
    val data = ArrayList<String>()
    data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603705847298&di=867c2c771e35479abd650d27f2062262&imgtype=0&src=http%3A%2F%2Fpic3.58cdn.com.cn%2Fzhuanzh%2Fn_v2fdd89f12343448c48244bb75c8aaa640.jpg%3Fw%3D750%26h%3D0")
    data.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603705847298&di=867c2c771e35479abd650d27f2062262&imgtype=0&src=http%3A%2F%2Fpic3.58cdn.com.cn%2Fzhuanzh%2Fn_v2fdd89f12343448c48244bb75c8aaa640.jpg%3Fw%3D750%26h%3D0")
    return data
}


/**
 * 分页拓展方法
 */
fun <T> BaseQuickAdapter<T, BaseViewHolder>.setPageData(
    page: Int,
    data: ArrayList<T>,
    first: (() -> Unit)? = null,
    other: (() -> Unit)? = null
) {
    if (page == 1) {
        setNewInstance(data)
        first?.invoke()
    } else {
        addData(data)
        other?.invoke()
    }
}

/**
 * 设置颜色
 */
fun Context.contextColor(color: Int): Int {
    return ContextCompat.getColor(this, color)
}

/**
 * drawable设置资源
 */
fun Context.contextDrawable(id: Int): Drawable {
    return ContextCompat.getDrawable(this, id)!!
}

/**
 * mmkv对象
 */
fun mmkv(): MMKV {
    return MMKV.defaultMMKV()
}


/**
 * 安装apk
 */
fun installApk(context: Context, downloadApk: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    val file = File(downloadApk)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        var uri =
            FileProvider.getUriForFile(context, "com.lyl.chaoji.fileProvider", file)
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    } else {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val uri = Uri.fromFile(file)
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
    }
    context.startActivity(intent)
}

fun installApkCompetence(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val hasInstallPermission = context.packageManager.canRequestPackageInstalls()
        if (!hasInstallPermission) {
            val packageURI: Uri = Uri.parse("package:" + context.packageName)
            val intent =
                Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
            (context as Activity).startActivityForResult(intent, 1000)
        } else {
            installApk(context, updatePath(context))
        }
    } else {
        installApk(context, updatePath(context))
    }
}

/**
 * 更新地址
 */
fun updatePath(context: Context): String {
    return context.externalCacheDir?.absolutePath + File.separator + "dachaoji.apk"
}

/**
 * 下载文件
 */
fun fileDownloader(
    context: Context, downloadUrl: String,
    completed: (() -> Unit)? = null,
    pending: (() -> Unit)? = null,
    progress: ((soFarBytes: Int, totalBytes: Int) -> Unit)? = null, ) {
    FileDownloader.setup(context)
    FileDownloader.getImpl().create(downloadUrl)
        .setPath(updatePath(context))
        .setListener(object : FileDownloadListener() {
            override fun completed(task: BaseDownloadTask?) {
                completed?.invoke()
            }

            override fun error(task: BaseDownloadTask?, e: Throwable?) {
            }

            override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
            }

            override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                pending?.invoke()
            }

            override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) {
                progress?.invoke(soFarBytes, totalBytes)
            }

            override fun warn(task: BaseDownloadTask?) {
            }
        }).start()
}

/**
 * 是否登陆
 */
fun isLogin(): Boolean {
    val token = mmkv().decodeString(Constant.TOKEN)
    return !token.isNullorEmpty()
}