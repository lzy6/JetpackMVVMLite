package com.example.jetpackmvvmlight.ui.fragment

import android.view.View
import com.example.commonlib.base.BaseFragment
import com.example.commonlib.getLayoutView
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.FragmentTestBinding

class TestFragment : BaseFragment() {

    private val viewBind by viewBinding(FragmentTestBinding::bind)

    override fun layoutView(): View {
        return getLayoutView(requireContext(), R.layout.fragment_test)
    }

    override fun lazyFetchData() {

    }

    override fun request() {

    }
}