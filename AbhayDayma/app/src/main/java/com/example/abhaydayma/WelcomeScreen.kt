package com.example.abhaydayma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val registerBtn = findViewById<Button>(R.id.registerBtn)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        registerBtn.setOnClickListener {
            startActivity(Intent(this@WelcomeScreen,OtpVerificationActivity::class.java ))
            finish()
        }

        loginBtn.setOnClickListener {
            var intent = Intent(this@WelcomeScreen,OtpVerificationActivity::class.java )
            intent.putExtra("flag" , "1" )
            startActivity(intent)
            finish()
        }
    }
}