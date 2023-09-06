package com.example.abhaydaymaassignment

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&apiKey=
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "d3d799640f794860af2d9c8d48f84e71"

interface ImageInterface{
    @GET("v2/top-headlines?country=us&apiKey=$API_KEY")
    fun getImage(@Query("page") page:Int): Call<News>
}

object NewsService{
     lateinit var  newsInstance: ImageInterface
     init {
         val retrofit = Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
         newsInstance = retrofit.create(ImageInterface::class.java)
     }
}