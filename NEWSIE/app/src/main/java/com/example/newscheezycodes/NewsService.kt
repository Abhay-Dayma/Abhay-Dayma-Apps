package com.example.newscheezycodes

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

// Apple https://newsapi.org/v2/everything?q=apple&from=2023-05-23&to=2023-05-23&sortBy=popularity&apiKey=d3d799640f794860af2d9c8d48f84e71

// Tesla https://newsapi.org/v2/everything?q=tesla&from=2023-04-24&sortBy=publishedAt&apiKey=d3d799640f794860af2d9c8d48f84e71

// Top Headlines https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d3d799640f794860af2d9c8d48f84e71

// TechCrunch https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=d3d799640f794860af2d9c8d48f84e71

// Wall-Street https://newsapi.org/v2/everything?domains=wsj.com&apiKey=d3d799640f794860af2d9c8d48f84e71

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "d3d799640f794860af2d9c8d48f84e71"

interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country:String, @Query("page") page:Int) : Call<News>

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getcategory(@Query("country") country:String, @Query("category") category: String, @Query("page") page:Int) : Call<News>
    //https://newsapi.org/v2/top-headlines?apiKey=d3d799640f794860af2d9c8d48f84e71&country=in&page=1

}

object NewsService{
    val newsInstance :NewsInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}
