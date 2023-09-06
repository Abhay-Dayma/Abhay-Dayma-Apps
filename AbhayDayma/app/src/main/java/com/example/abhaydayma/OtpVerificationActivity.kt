package com.example.abhaydayma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpVerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        val btnTakeNumber = findViewById<Button>(R.id.takeNumberBtn)
        var getPhoneNumber = findViewById<EditText>(R.id.phoneNumber)
        var phoneNumber = ""

        var flag = intent.getStringExtra("flag")

        btnTakeNumber.setOnClickListener {
           phoneNumber = getPhoneNumber.text.toString()
            //val phoneInt = Integer.valueOf(phoneNumber)
            if(phoneNumber==null){
                Toast.makeText(this@OtpVerificationActivity, "Please Enter Valid Number", Toast.LENGTH_LONG).show()
            }



            val intent = Intent(this, GetOtp::class.java)

            var result = ApiService.resultApiInstance.sendOtp(phoneNumber)
            result.enqueue(object: Callback<ApiReturnValues>{
                override fun onResponse(
                    call: Call<ApiReturnValues>,
                    response: Response<ApiReturnValues>
                ) {
                   val getResult = response.body()
                    getResult?.let {
                        if(it.Status == "Success"){
                            Toast.makeText(this@OtpVerificationActivity, "Success", Toast.LENGTH_LONG).show()
                            intent.putExtra("details", it.Details)
                            intent.putExtra("flag", flag)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this@OtpVerificationActivity, "Error Occurred", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ApiReturnValues>, t: Throwable) {
                    Toast.makeText(this@OtpVerificationActivity, "Network Problem!", Toast.LENGTH_LONG).show()
                }

            })

        }
    }
}
