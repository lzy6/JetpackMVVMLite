package com.example.jetpackmvvmlight.ui.activity.main.view

import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.GravityCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonlib.*
import com.example.commonlib.app.RoutePath
import com.example.commonlib.base.BaseActivity
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.databinding.ActivityMainBinding
import com.example.jetpackmvvmlight.ui.adapter.MainAdapter
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(R.layout.activity_main),
    NavigationView.OnNavigationItemSelectedListener {
    private val viewBind by viewBinding(ActivityMainBinding::inflate)
    private val adapter by lazy { MainAdapter() }

    override fun initData() {
        init()
        initClick()
        initRv()
        initListener()

    }

    override fun request() {

    }

    /**
     * 初始化点击事件
     */
    private fun initClick() {
        viewBind.includeMain.fab.onClick {
            snackBarToast(viewBind.includeMain.fab, R.string.issue)
        }
    }

    /**
     * 初始化listener
     */
    private fun initListener() {
        adapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(this, RvDetailActivity::class.java)
            val activityOptions =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    Pair<View, String>(view.findViewById(R.id.iv_img), RvDetailActivity.IMAGE),
                    Pair<View, String>(view.findViewById(R.id.tv_title), RvDetailActivity.TITLE)
                )

            ActivityCompat.startActivity(
                this,
                intent,
                activityOptions.toBundle()
            )
        }
    }

    /**
     * 初始化recyclerview
     */
    private fun initRv() {
        viewBind.includeMain.rvList.adapter = adapter
        adapter.setNewInstance(data())
    }

    /**
     * 初始化toolbar
     */
    private fun init() {
        setSupportActionBar(viewBind.includeMain.toolbar)
        ActionBarDrawerToggle(
            this,
            viewBind.drawerLayout,
            viewBind.includeMain.toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ).run {
            viewBind.drawerLayout.addDrawerListener(this)
            syncState()
        }

        viewBind.viewNav.setNavigationItemSelectedListener(this)
        viewBind.viewNav.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_announcement -> startKtxActivity<AnnouncementActivity>()
            R.id.nav_settings -> ARouter.getInstance().build(RoutePath.SettingActivity)
                .navigation()
        }
        viewBind.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}