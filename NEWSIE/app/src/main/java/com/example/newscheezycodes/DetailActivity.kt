package com.example.newscheezycodes

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val url:String? = intent.getStringExtra("URL")
        val detailWebView = findViewById<WebView>(R.id.detailWebView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        try {
            if (url != null) {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(this, Uri.parse(url))
                progressBar.visibility = View.GONE
                this.finish()
            }
        }catch(e:Exception){
            if (url != null) {
                detailWebView.settings.javaScriptEnabled = true
                detailWebView.settings.userAgentString =
                    "Mozilla/5.0 (Android 13; Mobile; LG-M255; rv:113.0) Gecko/113.0 Firefox/113.0"
                detailWebView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        progressBar.visibility = View.GONE
                        detailWebView.visibility = View.VISIBLE
                    }
                }
                detailWebView.loadUrl(url)
            }
        }
    }
}