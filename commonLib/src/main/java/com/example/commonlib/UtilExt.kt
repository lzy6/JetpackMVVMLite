package com.example.commonlib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.commonlib.app.Constant
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch
import java.io.File


/**
 * List<*>
 * 判断空或没数据
 */
fun List<*>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

/**
 * 任意类型
 * 判断是空
 */
fun Any?.isNull(): Boolean = this == null

/**
 * 任意类型
 * 判断不是空
 */
fun Any?.isNotNull(): Boolean = this != null

/**
 * String
 * 判断空或没数据
 */

fun String?.isNullorEmpty(): Boolean = this == null || this.isEmpty()

/**
 * 根据布局id获取view
 */
fun getLayoutView(context: Context, layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, null, false)
}

/**
 * 分页拓展方法
 */
fun <T, VB : ViewBinding> BaseQuickAdapter<T, BindingViewHolder<VB>>.setPageData(
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
 * drawable设置资源
 */
fun Context.drawable(id: Int): Drawable =
    ContextCompat.getDrawable(this, id)!!

/**
 * 设置color选择器
 */
fun Context.colorStateList(id: Int): ColorStateList =
    ContextCompat.getColorStateList(this, id)!!


/**
 * mmkv对象
 */
fun mmkv(): MMKV =
    MMKV.defaultMMKV()


/**
 * 安装apk
 */
fun installApk(context: Context, downloadApk: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    val file = File(downloadApk)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val uri =
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
fun updatePath(context: Context): String =
    context.externalCacheDir?.absolutePath + File.separator + "dachaoji.apk"


/**
 * 下载文件
 */
fun fileDownloader(
    context: Context, downloadUrl: String,
    completed: (() -> Unit)? = null,
    pending: (() -> Unit)? = null,
    progress: ((soFarBytes: Int, totalBytes: Int) -> Unit)? = null,
) {
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

/**
 * 屏幕宽度
 */
fun Context.screenWidth(): Int {
    return resources.displayMetrics.widthPixels
}

/**
 * 屏幕高度
 */
fun Context.screenHeight(): Int {
    return resources.displayMetrics.heightPixels
}


