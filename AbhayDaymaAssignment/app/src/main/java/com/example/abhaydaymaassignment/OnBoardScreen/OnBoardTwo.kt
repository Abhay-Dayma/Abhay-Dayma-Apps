package com.example.abhaydaymaassignment.OnBoardScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.abhaydaymaassignment.R

class OnBoardTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_two)
        findViewById<ImageView>(R.id.onBoard2Img).setOnClickListener {
            startActivity(Intent(this, OnBoardThree::class.java))
            finish()
        }
    }
}