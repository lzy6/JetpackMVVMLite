package com.example.commonlib.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.*
import com.example.commonlib.app.AppManager
import com.example.commonlib.entity.State
import com.lltt.qmuilibrary.dialog.QMUITipDialog
import com.lltt.qmuilibrary.util.QMUIStatusBarHelper

abstract class BaseActivity : AppCompatActivity() {

    protected val TAG = this::class.java.simpleName

    private val mQMUILoadingDialog by lazy {
        QMUITipDialog.Builder(this)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.getAppManager().addActivity(this)
        ARouter.getInstance().inject(this)
        initData()
        initToolbar()
        request()
    }

    /**
     * 初始化
     */
    abstract fun initData()

    /**
     * 请求数据及回调处理
     */
    abstract fun request()

    /**
     * 初始化标题栏，添加返回事件
     */
    protected open fun initToolbar() {

        (findViewById<TextView>(R.id.toolbar_title))?.run {
            if (title.isNullOrEmpty()) return@run
            text = title
        }
        (findViewById<RelativeLayout>(R.id.toolbar_back))?.run {
            setOnClickListener { v ->
                onBackPressed()
            }
        }
    }


    /**
     * 显示loading弹框
     */
    private fun showLoading() {
        if (!mQMUILoadingDialog.isShowing) {
            mQMUILoadingDialog.show()
        }
    }

    /**
     * 关闭loading弹框
     */
    private fun dismissLoading() {
        if (mQMUILoadingDialog.isShowing) {
            mQMUILoadingDialog.dismiss()
        }
    }

    /**
     * 设置状态栏
     * @param isLightMode true-黑色，false-白色
     */
    protected fun initStatusBar(view: View, isLightMode: Boolean) {
        QMUIStatusBarHelper.translucent(this)
        view.toolbarPadding()
        initNoViewStatusBar(isLightMode)
    }

    /**
     * 设置状态栏
     * @param isLightMode true-黑色，false-白色
     */
    protected fun initStatusBar(view: View) {
        QMUIStatusBarHelper.translucent(this)
        view.toolbarPadding()
    }


    /**
     * 没有view的状态栏
     * @param isLightMode true-黑色，false-白色
     */
    protected fun initNoViewStatusBar(isLightMode: Boolean) {
        QMUIStatusBarHelper.translucent(this)
        statusColor(this, isLightMode)
    }


    /**
     * dialog状态
     */
    fun dialogState(state: State) {
        apiState(
            state,
            before = { showLoading() },
            complete = { dismissLoading() })
    }

    /**
     * 接口方法
     */
    fun <T> observe(
        liveData: StateLiveData<T>,
        action: (t: T) -> Unit,
    ): StateLiveData<T> {
        liveData.observeInActivity(this) { it?.let { t -> action(t) } }
        return liveData
    }

    /**
     * 结果状态
     */
    fun <T> StateLiveData<T>.toState(action: (t: State) -> Unit) {
        state.observeInActivity(this@BaseActivity) { action(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager().removeActivity(this)
    }

}
