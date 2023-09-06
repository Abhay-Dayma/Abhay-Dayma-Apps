package com.example.abhaydaymaassignment.OnBoardScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.abhaydaymaassignment.MainActivity
import com.example.abhaydaymaassignment.R

class OnBoardOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_one)

        findViewById<Button>(R.id.skipButton).setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        var preference = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        var firstTime = preference.getString("FirstTimeInstall","")

        if(firstTime.equals("YES")){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<ImageView>(R.id.onBoard1Img).setOnClickListener {
            startActivity(Intent(this, OnBoardTwo::class.java))
            finish()
        }
    }
}