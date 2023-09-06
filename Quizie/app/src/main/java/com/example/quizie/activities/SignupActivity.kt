package com.example.quizie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizie.R
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()

        val btnSignup =  findViewById<Button>(R.id.btnSignUp)
        btnSignup.setOnClickListener {
            signUpUser()
        }

        val alreadyAccount = findViewById<TextView>(R.id.alreadyAccount)
        alreadyAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun signUpUser(){
        val email = findViewById<TextView>(R.id.etEmailAddress).text.toString()
        val password = findViewById<TextView>(R.id.etPassword).text.toString()
        val confirmPassword = findViewById<TextView>(R.id.etConfirmPassword).text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Don't leave any field as blank", Toast.LENGTH_LONG).show()
            return
        }
        if(password.equals(confirmPassword).not()){
            Toast.makeText(this, "Password and confirm password not the same.", Toast.LENGTH_LONG).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Sign Up successful!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this, "Error creating user", Toast.LENGTH_LONG).show()
                }
            }

    }
}