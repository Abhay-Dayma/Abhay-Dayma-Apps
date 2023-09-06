package com.example.abhaydaymaassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.feed).setOnClickListener{
            startActivity(Intent(this, Feeds::class.java))
        }

        findViewById<Button>(R.id.uploadImage).setOnClickListener{
            startActivity(Intent(this, UploadImage::class.java))
        }


    }
}