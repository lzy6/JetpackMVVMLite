package com.example.jetpackmvvmlight.app.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lltt.qmuilibrary.dialog.QMUITipDialog
import com.lltt.qmuilibrary.util.QMUIStatusBarHelper

abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context

    private var isFirstLoad = true//是否是第一次加载

    //loadingDialog
    private val mQMUILoadingDialog by lazy {
        QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    abstract fun layoutView(): View

    abstract fun lazyFetchData()


    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            lazyFetchData()
            isFirstLoad = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isFirstLoad = true
    }

    /**
     * 显示loading弹框
     */
    protected fun showLoading() {
        mQMUILoadingDialog.show()
    }

    /**
     * 关闭loading弹框
     */
    protected fun dismissLoading() {
        mQMUILoadingDialog.dismiss()
    }

    /**
     * 根据bundle跳转activity
     */
    fun startActivity(clazz: Class<*>, params: Bundle) {
        val intent = Intent(context, clazz)
        intent.putExtras(params)
        context?.startActivity(intent)
    }

    /**
     * 直接跳转activity
     */
    fun startActivity(clazz: Class<*>) {
        context?.startActivity(Intent(context, clazz))
    }

    fun toolbarPadding(view: View) {
        view.run {
            setPadding(
                paddingLeft,
                QMUIStatusBarHelper.getStatusBarHeight(mContext),
                paddingRight,
                paddingBottom
            )
            val linearParams = layoutParams
            linearParams.height += QMUIStatusBarHelper.getStatusBarHeight(mContext)
            layoutParams = linearParams
        }
    }


}
