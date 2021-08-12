package com.example.commonlib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.commonlib.apiState
import com.example.commonlib.entity.State
import com.lltt.qmuilibrary.dialog.QMUITipDialog
import com.lltt.qmuilibrary.util.QMUIStatusBarHelper

abstract class BaseFragment : Fragment() {

    protected lateinit var mContext: Context

    private var isFirstLoad = true//是否是第一次加载


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

    /**
     * 请求数据及回调处理
     */
    abstract fun request()

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            lazyFetchData()
            request()
            isFirstLoad = false
        }
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

    /**
     * 接口方法
     */
    fun <T> observe(
        liveData: StateLiveData<T>,
        action: (t: T) -> Unit,
    ): StateLiveData<T> {
        liveData.observeInFragment(this) { it?.let { t -> action(t) } }
        return liveData
    }

    /**
     * 结果状态
     */
    fun <T> StateLiveData<T>.toState(action: (t: State) -> Unit) {
        state.observeInFragment(this@BaseFragment) { action(it) }
    }


}
