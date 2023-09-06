package com.example.newscheezycodes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SelectedNews : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var article = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = 1
    val TAG = "MainActivity"
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var ref:String
    lateinit var countryCode:String
    var status = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_news)
        ref =  intent.getStringExtra("ref").toString()
        countryCode = intent.getStringExtra("country").toString()
        status = intent.getStringExtra("status").toBoolean()


        MobileAds.initialize(this)


       var mAdView = findViewById<AdView>(R.id.adView)
        val adRequest1 = AdRequest.Builder().build()
        mAdView.loadAd(adRequest1)

        var mAdView2 = findViewById<AdView>(R.id.adView2)
        val adRequest2 = AdRequest.Builder().build()
        mAdView2.loadAd(adRequest2)


        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.toString()!!)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        adapter = NewsAdapter(this@SelectedNews, article)
        val newsList = findViewById<RecyclerView>(R.id.newsList)
        newsList.adapter = adapter
       // newsList.layoutManager = LinearLayoutManager(this@SelectedNews)
        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)

        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        val container = findViewById<ConstraintLayout>(R.id.container)
        var count = 1
        layoutManager.setItemChangedListener(object: StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                count++
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d(TAG, "First Visible Item - ${layoutManager.getFirstVisibleItemPosition()}")
                Log.d(TAG, "Total Count - ${layoutManager.itemCount}")
                if(totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5){
                    //next page
                    pageNum++
                    getNews()
                }
                if(count % 17 == 0){
                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(this@SelectedNews)
                        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                            override fun onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad showed fullscreen content.")
                                InterstitialAd.load(this@SelectedNews,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
                                    override fun onAdFailedToLoad(adError: LoadAdError) {
                                        Log.d(TAG, adError?.toString()!!)
                                        mInterstitialAd = null
                                    }

                                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                                        Log.d(TAG, "Ad was loaded.")
                                        mInterstitialAd = interstitialAd
                                    }
                                })
                            }
                        }
                        } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet.")
                    }
                }
            }
        })

        newsList.layoutManager = layoutManager


        getNews()
    }

    private fun getNews() {
        Log.d(TAG, "Request sent for $pageNum")
        Log.d(TAG, "this is $countryCode and $status")

        if(status){

            val news = NewsService.newsInstance.getcategory(countryCode,"general", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nTechnology\nIndia\nNews"){

            val news = NewsService.newsInstance.getcategory("in","technology", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }
        else if(ref == "\n\nBusiness\nIndia\nNews"){

            val news = NewsService.newsInstance.getcategory("in","business", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })

        }
        else if(ref == "\n\nHealth\nIndia\nNews"){
            val news = NewsService.newsInstance.getcategory("in","health", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nEntertainment\nIndia\nNews"){
            val news = NewsService.newsInstance.getcategory("in","entertainment", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nScience\nIndia\nNews"){
            val news = NewsService.newsInstance.getcategory("in","science", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nSports\nIndia\nNews"){
            val news = NewsService.newsInstance.getcategory("in","sports", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nGeneral\nIndia\nNews"){
            val news = NewsService.newsInstance.getcategory("in","general", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nTechnology\nAmerica\nNews"){

            val news = NewsService.newsInstance.getcategory("us","technology", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })

        }
        else if(ref == "\n\nBusiness\nAmerica\nNews"){

            val news = NewsService.newsInstance.getcategory("us","business", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })

        }
        else if(ref == "\n\nHealth\nAmerica\nNews"){
            val news = NewsService.newsInstance.getcategory("us","health", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nEntertainment\nAmerica\nNews"){
            val news = NewsService.newsInstance.getcategory("us","entertainment", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nScience\nAmerica\nNews"){
            val news = NewsService.newsInstance.getcategory("us","science", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nSports\nAmerica\nNews"){
            val news = NewsService.newsInstance.getcategory("us","sports", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nGeneral\nAmerica\nNews"){
            val news = NewsService.newsInstance.getcategory("us","general", pageNum)
            news.enqueue(object: Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if(news != null){
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else if(ref == "\n\nAmerican\nNews\nHeadLines"){
            val news = NewsService.newsInstance.getHeadlines("us", pageNum)
            news.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if (news != null) {
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }

        else {
            val news = NewsService.newsInstance.getHeadlines("in", pageNum)
            news.enqueue(object : Callback<News> {
                override fun onResponse(call: Call<News>, response: Response<News>) {
                    val news = response.body()
                    if (news != null) {
                        totalResults = news.totalResults
                        Log.d("CHEEZYCODES", news.toString())
                        article.addAll(news.articles)
                        adapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("CHEEZYCODES", "Error in Fetching News", t)
                }
            })
        }
    }
}