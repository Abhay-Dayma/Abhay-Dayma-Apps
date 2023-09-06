package com.example.quizie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizie.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btnGetStarted).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}