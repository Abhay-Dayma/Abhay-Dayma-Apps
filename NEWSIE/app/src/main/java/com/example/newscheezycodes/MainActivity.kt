package com.example.newscheezycodes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

var INDIA = true
var USA = false
var OTHER = false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listNews : Vector<NewsKind> = Vector<NewsKind>()

//        listNews.add(NewsKind(R.drawable.india,"\n\nIndian\nNews\nHeadLines"))
//        listNews.add(NewsKind(R.drawable.technology1, "\n\nTechnology\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.business1, "\n\nBusiness\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.health2, "\n\nHealth\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.entertainment2, "\n\nEntertainment\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.science1, "\n\nScience\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.sport2, "\n\nSports\nIndia\nNews"))
//        listNews.add(NewsKind(R.drawable.general1, "\n\nGeneral\nIndia\nNews"))
//
//        //listNews.add(NewsKind(R.drawable.tesla, "\n\nTesla\nNews"))
//        //listNews.add(NewsKind(R.drawable.wtstreet, "\n\nWall\nStreet\nNews"))
//        //listNews.add(NewsKind(R.drawable.apple, "\n\nApple\nNews"))
//        listNews.add(NewsKind(R.drawable.american, "\n\nAmerican\nNews\nHeadLines"))
//        listNews.add(NewsKind(R.drawable.techno, "\n\nTechnology\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.business2, "\n\nBusiness\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.health1, "\n\nHealth\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.entertainment1, "\n\nEntertainment\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.science2, "\n\nScience\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.sports1, "\n\nSports\nAmerica\nNews"))
//        listNews.add(NewsKind(R.drawable.general2, "\n\nGeneral\nAmerica\nNews"))

        //openIndiaNews(listNews)

        val recycle1 = findViewById<RecyclerView>(R.id.recycle1)

        openIndiaNews(listNews, recycle1, this@MainActivity)

        var imgUsa = findViewById<ImageView>(R.id.usaImgView)
        imgUsa.setOnClickListener {
            openUsaNews(listNews,  recycle1, this@MainActivity)
        }

        var imgInd = findViewById<ImageView>(R.id.indiaImgView)
        imgInd.setOnClickListener {
            openIndiaNews(listNews, recycle1, this@MainActivity)
        }

        var imgGlobal = findViewById<ImageView>(R.id.otherImgView)
        imgGlobal.setOnClickListener {
            val intent = Intent(this@MainActivity, OtherNews::class.java)
            this@MainActivity.startActivity(intent)
        }

        recycle1.adapter = MyAdapter1(this,listNews)
        recycle1.layoutManager = LinearLayoutManager(this)
    }
}

fun openIndiaNews(listNews: Vector<NewsKind>, recycle1: RecyclerView, context: Context){

    listNews.clear()

    listNews.add(NewsKind(R.drawable.india,"\n\nIndian\nNews\nHeadLines"))
    listNews.add(NewsKind(R.drawable.technology1, "\n\nTechnology\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.business1, "\n\nBusiness\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.health2, "\n\nHealth\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.entertainment2, "\n\nEntertainment\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.science1, "\n\nScience\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.sport2, "\n\nSports\nIndia\nNews"))
    listNews.add(NewsKind(R.drawable.general1, "\n\nGeneral\nIndia\nNews"))

    recycle1.adapter = MyAdapter1(context,listNews)
    recycle1.layoutManager = LinearLayoutManager(context)
}

fun openUsaNews(listNews: Vector<NewsKind>, recycle1: RecyclerView, context: Context){

    listNews.clear()

    listNews.add(NewsKind(R.drawable.american, "\n\nAmerican\nNews\nHeadLines"))
    listNews.add(NewsKind(R.drawable.techno, "\n\nTechnology\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.business2, "\n\nBusiness\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.health1, "\n\nHealth\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.entertainment1, "\n\nEntertainment\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.science2, "\n\nScience\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.sports1, "\n\nSports\nAmerica\nNews"))
    listNews.add(NewsKind(R.drawable.general2, "\n\nGeneral\nAmerica\nNews"))

    recycle1.adapter = MyAdapter1(context,listNews)
    recycle1.layoutManager = LinearLayoutManager(context)
}