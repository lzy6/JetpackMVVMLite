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
import com.example.commonlib.app.RoutePath
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.data
import com.example.commonlib.onClick
import com.example.commonlib.snackBarToast
import com.example.jetpackmvvmlight.R
import com.example.jetpackmvvmlight.ui.adapter.MainAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : BaseActivity(R.layout.activity_main),
    NavigationView.OnNavigationItemSelectedListener {

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
        fab.onClick {
            snackBarToast(fab, R.string.issue)
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
                    Pair<View, String>(view.iv_img, RvDetailActivity.IMAGE),
                    Pair<View, String>(view.tv_title, RvDetailActivity.TITLE)
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
        rv_list.adapter = adapter
        adapter.setNewInstance(data())
    }

    /**
     * 初始化toolbar
     */
    private fun init() {
        setSupportActionBar(toolbar)
        ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close
        ).run {
            drawer_layout.addDrawerListener(this)
            syncState()
        }

        view_nav.setNavigationItemSelectedListener(this)
        view_nav.itemIconTintList = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_announcement -> startActivity(AnnouncementActivity::class.java)
            R.id.nav_settings -> ARouter.getInstance().build(RoutePath.SettingActivity)
                .navigation()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}