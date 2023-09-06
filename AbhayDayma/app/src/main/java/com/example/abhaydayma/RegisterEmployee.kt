package com.example.abhaydayma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterEmployee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_employee)

        findViewById<Button>(R.id.submitBtn).setOnClickListener{
            var empName = findViewById<EditText>(R.id.getEmpName).text
            var empPhone = findViewById<EditText>(R.id.getEmpPhoneNumber).text
            var empMail = findViewById<EditText>(R.id.getEmpEmail).text
            var empReportTo = findViewById<EditText>(R.id.getReportsTo).text
            var empImageProfile = findViewById<EditText>(R.id.getUrlToImage).text

            var intent = Intent(this@RegisterEmployee, MainActivity::class.java)
            intent.putExtra("name", empName.toString())
            intent.putExtra("phone", empPhone.toString())
            intent.putExtra("mail", empMail.toString())
            intent.putExtra("report", empReportTo.toString())
            intent.putExtra("image", empImageProfile.toString())
            intent.putExtra("flag", "1")
            startActivity(intent)

        }
    }
}