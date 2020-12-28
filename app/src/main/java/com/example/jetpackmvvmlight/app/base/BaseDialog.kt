package com.example.jetpackmvvmlight.app.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.jetpackmvvmlight.R
import com.lltt.qmuilibrary.util.QMUIDisplayHelper


abstract class BaseDialog : Dialog {

    protected lateinit var mContext: Context

    protected var mHeight = 0.75f
    protected var mWidth = 0.8f

    constructor(context: Context) : super(context, R.style.BaseDialog) {
        mContext = context
    }

    constructor(context: Context, theme: Int) : super(context, theme) {}

    protected constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener
    ) : super(context, cancelable, cancelListener) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onCreateView())

    }

    abstract fun onCreateView(): View

    abstract fun setUiBeforShow()


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()


        var view = window!!.decorView
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )

        val m: WindowManager = (mContext as Activity).windowManager
        val display = m.defaultDisplay
        val p: WindowManager.LayoutParams = window?.attributes!!
        if (QMUIDisplayHelper.getScreenHeight(context) * 0.75f < view.measuredHeight) {
            p.height = ((display.height * mHeight).toInt())
        }
        p.width = ((display.width * 0.9).toInt())
        window?.attributes = p

        setUiBeforShow()
    }
}
