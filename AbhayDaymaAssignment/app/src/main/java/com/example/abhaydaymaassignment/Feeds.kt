package com.example.abhaydaymaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Feeds : AppCompatActivity() {
    private val article = mutableListOf<Article>()
    private val sendingArticles = mutableListOf<Article>()
    private val countArticle = 0
    private var totalResults = 1
    private var pageNumber = 1
    lateinit var adapter: MyAdapter
    lateinit var swipeRefresh:SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeds)
        getImage()

        adapter = MyAdapter(this@Feeds, sendingArticles)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)

        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        var swipe = SwipeRefreshLayout(this)

        swipeRefresh.setOnRefreshListener {
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show()
            //updateArticles() //refresh your list contents somehow
            swipeRefresh.isRefreshing = false   // reset the SwipeRefreshLayout (stop the loading spinner)
        }

//        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            @Override
//            fun onRefresh(){
//                Toast.makeText(this, "Refreshing...", Toast.LENGTH_LONG).show()
//                updateArticles()
//                swipeRefresh.isRefreshing = false
//            }
//        })


    }

    private fun updateArticles(){
        sendingArticles.clear()

        for( i in count until count+10){
            sendingArticles.add(article.get(i))
        }
        count = count+ 10
    }

    private fun getImage(){
        val imageObject = NewsService.newsInstance.getImage(1)
        imageObject.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                news?.let {
                    Log.d("TAG", news.toString())
                    totalResults = news.totalResults
                    article.addAll(news.articles)
                    updateArticles()
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@Feeds, "No Internet", Toast.LENGTH_LONG).show()
            }
        })
    }
}