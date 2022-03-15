package com.example.jetpackmvvmlight.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.commonlib.base.BaseFragment
import com.example.commonlib.getLayoutView
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.FragmentTestBinding

class TestFragment : BaseFragment() {

    private val viewBind by viewBinding(FragmentTestBinding::bind)
    private val text by lazy { arguments?.getString("text","") }

    companion object {
        fun newInstance(text: String):TestFragment {
            val fragment = TestFragment()
            val bundle = Bundle().apply {
                putString("text", text)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutView(): View {
        return getLayoutView(requireContext(), R.layout.fragment_test)
    }

    override fun lazyFetchData() {
        viewBind.tvText.text = text
    }

    override fun request() {

    }
}