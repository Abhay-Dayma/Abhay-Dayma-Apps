package com.example.browsie

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<ImageView>(R.id.imageView)

        val url ="https://duckduckgo.com"

        btn.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }

        val cowurl = "https://swisscows.com/en"

        val btn2 = findViewById<ImageView>(R.id.imageView2)
        btn2.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(cowurl))
        }

        val startpage = "https://www.startpage.com/"
        val img3 = findViewById<ImageView>(R.id.imageView3)
        img3.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(startpage))
        }
        val searchEncrypt = "https://www.searchencrypt.com/home"
        val btn4 = findViewById<ImageView>(R.id.imageView4)
        btn4.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(searchEncrypt))
        }

        val ecosia = "https://www.ecosia.org/"

        val btn5 = findViewById<ImageView>(R.id.imageView5)
        btn5.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(ecosia))
        }

        val lukol = "https://www.lukol.com/"
        val btn6 = findViewById<ImageView>(R.id.imageView6)
        btn6.setOnClickListener{
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(lukol))
        }

    }
}