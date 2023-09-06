package com.example.abhaydaymaassignment.OnBoardScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.abhaydaymaassignment.MainActivity
import com.example.abhaydaymaassignment.R

class OnBoardThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_three)

        var preference = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        var firstTime = preference.getString("FirstTimeInstall","")

        findViewById<Button>(R.id.readyBtn).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            var editor = preference.edit()
            editor.putString("FirstTimeInstall", "YES")
            editor.apply()
            finish()
        }
    }
}