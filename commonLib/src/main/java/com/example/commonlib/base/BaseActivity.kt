package com.example.commonlib.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.R
import com.example.commonlib.app.AppManager
import com.lltt.qmuilibrary.dialog.QMUITipDialog
import com.lltt.qmuilibrary.util.QMUIStatusBarHelper

abstract class BaseActivity(layoutId: Int) : AppCompatActivity(layoutId) {

    protected val TAG = this::class.java.simpleName

    private val mQMUILoadingDialog by lazy {
        QMUITipDialog.Builder(this)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initToolbar()
        initData()
        AppManager.getAppManager().addActivity(this)
    }

    abstract fun initData()

    /**
     * 初始化标题栏，添加返回事件
     */
    protected open fun initToolbar() {
        if (findViewById<TextView>(R.id.toolbar_title) != null) {
            (findViewById<TextView>(R.id.toolbar_title)).text = title
        }
        if (findViewById<RelativeLayout>(R.id.toolbar_back) != null) {
            findViewById<RelativeLayout>(R.id.toolbar_back).setOnClickListener { v ->
                onBackPressed()
            }
        }
    }

    /**
     * 显示loading弹框
     */
    protected fun showLoading() {
        if (!mQMUILoadingDialog.isShowing) {
            mQMUILoadingDialog.show()
        }
    }

    /**
     * 关闭loading弹框
     */
    protected fun dismissLoading() {
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
        toolbarPadding(view)
        statusColor(isLightMode)
    }


    /**
     * 没有view的状态栏
     * @param isLightMode true-黑色，false-白色
     */
    protected fun initNoViewStatusBar(isLightMode: Boolean) {
        QMUIStatusBarHelper.translucent(this)
        statusColor(isLightMode)
    }

    fun toolbarPadding(view: View) {
        view.run {
            setPadding(
                paddingLeft,
                QMUIStatusBarHelper.getStatusBarHeight(this@BaseActivity),
                paddingRight,
                paddingBottom
            )
            val linearParams = layoutParams
            linearParams.height += QMUIStatusBarHelper.getStatusBarHeight(this@BaseActivity)
            layoutParams = linearParams
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager().removeActivity(this)
    }

    /**
     * 直接跳转activity
     */
    fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    fun statusColor(isLightMode: Boolean) {
        if (isLightMode) {
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        } else {
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        }
    }

}
