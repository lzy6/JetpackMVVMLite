package com.example.commonlib.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.commonlib.screenHeight
import com.example.commonlib.screenWidth

abstract class BaseDialogFragment : DialogFragment() {

    private var isFirstLoad = true//是否是第一次加载
    protected var height = 0.75f
    protected var width = 0.8f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutView()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            lazyFetchData()
            isFirstLoad = false
        }
    }

    abstract fun layoutView(): View

    abstract fun lazyFetchData()

    override fun onStart() {
        super.onStart()
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.decorView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            val p: WindowManager.LayoutParams = it.attributes!!
            if (requireContext().screenHeight() * 0.75f < it.decorView.measuredHeight) {
                p.height = ((requireContext().screenHeight() * height).toInt())
            }
            p.width = ((requireContext().screenWidth() * width).toInt())
            it.attributes = p
        }
    }

}