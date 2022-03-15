package com.example.jetpackmvvmlight.ui.activity.main.view

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.databinding.ActivityTabViewPager2Binding
import com.example.jetpackmvvmlight.ui.fragment.TestFragment
import com.google.android.material.tabs.TabLayoutMediator
import androidx.core.content.ContextCompat
import com.example.commonlib.*
import com.example.jetpackmvvmlight.R


class TabViewPager2Activity : BaseActivity() {

    private val viewBind by viewBinding(ActivityTabViewPager2Binding::inflate)
    val tabs = arrayOf("最新", "关注", "推荐")
    val tabsText = arrayOf("指示器与tab等宽", "指示器固定宽度", "指示器跟随文字宽度", "带Icon的TabLayout(自定义布局)")

    override fun initData() {
        init()
        initClick()
        initViewPager()
        initTab()
    }

    override fun request() {

    }

    private fun initClick() {
        viewBind.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initViewPager() {
        vp1()
        vp2()
        vp3()
        vp4()
    }

    private fun vp1() {
        viewBind.pager.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            @NonNull
            override fun createFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabsText[0])
            }

            override fun getItemCount(): Int {
                return tabs.size
            }
        }
    }

    private fun vp2() {
        viewBind.pager2.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            @NonNull
            override fun createFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabsText[1])
            }

            override fun getItemCount(): Int {
                return tabs.size
            }
        }
    }

    private fun vp3() {
        viewBind.pager3.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            @NonNull
            override fun createFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabsText[2])
            }

            override fun getItemCount(): Int {
                return tabs.size
            }
        }
    }

    private fun vp4() {
        viewBind.pager4.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
            @NonNull
            override fun createFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabsText[3])
            }

            override fun getItemCount(): Int {
                return tabs.size
            }
        }
    }

    private fun initTab() {
        tab1()
        tab2()
        tab3()
        tab4()
    }

    private fun tab1() {
        TabLayoutMediator(viewBind.tabLayout, viewBind.pager) { tab, position ->
            tab.text=tabs[position]
        }.attach()
    }

    private fun tab2() {
        TabLayoutMediator(viewBind.tabLayout2, viewBind.pager2) { tab, position ->
            tab.text=tabs[position]
        }.attach()
    }

    private fun tab3() {
        TabLayoutMediator(viewBind.tabLayout3, viewBind.pager3) { tab, position ->
            tab.text=tabs[position]
        }.attach()
    }

    private fun tab4() {
        TabLayoutMediator(viewBind.tabLayout4, viewBind.pager4) { tab, position ->
            val view = getLayoutView(this, R.layout.tab_img_text).apply {
                findViewById<TextView>(R.id.tv_text).apply {
                    text = tabs[position]
                    setTextColor(colorStateList(R.color.selector_tab_text))
                }
                findViewById<ImageView>(R.id.iv_icon).apply {
                    when(position){
                        0 -> setImageDrawable(drawable(R.drawable.selector_home))
                        1 -> setImageDrawable(drawable(R.drawable.selector_home))
                        2 -> setImageDrawable(drawable(R.drawable.selector_user))
                    }

                }
            }
            tab.customView = view
        }.attach()
    }

}