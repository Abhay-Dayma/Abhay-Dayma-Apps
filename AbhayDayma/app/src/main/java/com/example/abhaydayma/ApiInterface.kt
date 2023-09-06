package com.example.abhaydayma

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
const val BASE_URL = "https://2factor.in/API/V1/b02a2fb3-09aa-11ee-addf-0200cd936042/"
interface ApiInterface {

@GET("SMS/{phoneNumber}/AUTOGEN/VM%20-%20FARMSB")
fun sendOtp(@Path("phoneNumber") phone: String):Call<ApiReturnValues>

@GET("SMS/VERIFY/{details}/{OTP_CODE}")
fun verifyOtp(@Path("details") details: String, @Path("OTP_CODE") OTP_CODE: String) : Call<ApiReturnValues>
}

object ApiService{
    val resultApiInstance: ApiInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        resultApiInstance = retrofit.create(ApiInterface::class.java)
    }
}