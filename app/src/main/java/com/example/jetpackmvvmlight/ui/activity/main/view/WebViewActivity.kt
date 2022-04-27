package com.example.jetpackmvvmlight.ui.activity.main.view

import android.content.Intent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.commonlib.app.Constant
import com.example.commonlib.base.BaseActivity
import com.example.commonlib.statusColor
import com.example.commonlib.viewBinding
import com.example.jetpackmvvmlight.databinding.ActivityWebViewBinding


class WebViewActivity : BaseActivity() {

    private val viewBind by viewBinding(ActivityWebViewBinding::inflate)
    private lateinit var webUrl: String

    override fun initData() {
        init()
        initClick()
    }

    override fun request() {

    }

    private fun initClick() {
        viewBind.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        statusColor(this, true)
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        webUrl = intent.getStringExtra(Constant.URL)!!

        viewBind.webview.run {
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    view.loadUrl(webUrl)
                    return true
                }
            }
            loadUrl(webUrl)
        }


    }


}