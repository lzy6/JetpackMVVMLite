package com.example.commonlib

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.commonlib.utils.FragmentViewBindingDelegate
import com.google.android.material.bottomsheet.BottomSheetDialog


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

/**
 * 设置DialogFragment viewbinding
 */
fun <T : ViewBinding> DialogFragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

/**
 * dialog viewBinding
 */
inline fun <reified VB : ViewBinding> Dialog.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB

/**
 * BottomSheetDialog
 * 从底部向上弹dialog，自定义布局T
 */
inline fun <reified T : ViewBinding> BottomSheetDialog.bindingBottomSheetDialog(): T {
    val binding: T by inflate()
    (binding.root.parent as View).setBackgroundColor(
        ContextCompat.getColor(
            context,
            android.R.color.transparent
        )
    )
    show()
    return binding
}

/**
 * dialog viewBinding
 */
inline fun <reified VB : ViewBinding> BottomSheetDialog.inflate() = lazy {
    inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
}