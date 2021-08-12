package com.example.commonlib

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.commonlib.app.Constant
import com.example.commonlib.utils.FragmentViewBindingDelegate
import com.example.commonlib.utils.UtilToast
import com.google.android.material.snackbar.Snackbar
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import com.tencent.mmkv.MMKV
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
fun Context.contextDrawable(id: Int): Drawable =
    ContextCompat.getDrawable(this, id)!!


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
 * 设置Activity viewbinding
 */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val invoke = bindingInflater.invoke(layoutInflater)
        setContentView(invoke.root) //可选
        invoke
    }

/**
 * 设置Fragment viewbinding
 */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

/**
 * adapter viewBinding
 */
class BindingViewHolder<VB : ViewBinding>(val binding: VB) : BaseViewHolder(binding.root)

inline fun <reified T : ViewBinding> newBindingViewHolder(parent: ViewGroup): BindingViewHolder<T> {
    val method = T::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )
    val binding = method.invoke(null, LayoutInflater.from(parent.context), parent, false) as T
    return BindingViewHolder(binding)
}


