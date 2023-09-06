package com.example.abhaydayma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOtp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_otp)
        var details = intent.getStringExtra("details").toString()
        var flag = intent.getStringExtra("flag")

        findViewById<Button>(R.id.checkOtp).setOnClickListener{
            var otpField = findViewById<EditText>(R.id.otpGetting)

            var result = ApiService.resultApiInstance.verifyOtp(details, otpField.text.toString())
            result.enqueue(object: Callback<ApiReturnValues>{
                override fun onResponse(
                    call: Call<ApiReturnValues>,
                    response: Response<ApiReturnValues>
                ) {
                    val getResult = response.body()
                    getResult?.let {
                        if(it.Status == "Success"){
                            Toast.makeText(this@GetOtp, "You're verified", Toast.LENGTH_LONG).show()

                            if(flag.equals("1")){
                                startActivity(Intent(this@GetOtp, MainActivity::class.java))
                            }
                            else{
                                startActivity(Intent(this@GetOtp, RegisterEmployee::class.java))
                            }
                            finish()
                        }
                        else{
                            Toast.makeText(this@GetOtp, "We're unable to verify you.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<ApiReturnValues>, t: Throwable) {
                    Toast.makeText(this@GetOtp, "Check your network.", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}