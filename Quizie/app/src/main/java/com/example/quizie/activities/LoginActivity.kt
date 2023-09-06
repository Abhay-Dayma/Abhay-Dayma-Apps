package com.example.quizie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizie.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()

        val loginbtn = findViewById<Button>(R.id.btnLogin)
        loginbtn.setOnClickListener {
            loginUser()
        }
        val btnToSignupActivity = findViewById<TextView>(R.id.btnToSignUpActi)
        btnToSignupActivity.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }

    private fun loginUser(){
        val email = findViewById<TextView>(R.id.etEmailAddress).text.toString()
        val password = findViewById<TextView>(R.id.etPassword).text.toString()


        if(email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Don't leave any field as blank", Toast.LENGTH_LONG).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Success!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "Authentication Failure", Toast.LENGTH_LONG).show()
                }
            }

    }
}