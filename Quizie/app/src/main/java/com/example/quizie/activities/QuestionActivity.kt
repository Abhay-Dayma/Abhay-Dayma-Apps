package com.example.quizie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizie.R
import com.example.quizie.adapter.OptionAdapter
import com.example.quizie.models.Question
import com.example.quizie.models.Quiz
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    var quizzes :MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        findViewById<Button>(R.id.previousBtn).setOnClickListener {
            index--
            if(index<questions!!.size){
                findViewById<Button>(R.id.submitBtn).visibility = View.GONE
                findViewById<Button>(R.id.nextBtn).visibility = View.VISIBLE
            }
            bindView()
        }

        findViewById<Button>(R.id.nextBtn).setOnClickListener {
            index++
            if(index == questions!!.size ) {
                findViewById<Button>(R.id.submitBtn).visibility = View.VISIBLE
                it.visibility = View.GONE
            }
            bindView()
        }

        findViewById<Button>(R.id.submitBtn).setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())

            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
       val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        date?.let {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(it != null && it.isEmpty.not()){
                        Log.d("Data", it.toObjects(Quiz::class.java).toString())
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindView()
                    }
                }
        }
    }

    private fun bindView() {
       val btnPrevious = findViewById<Button>(R.id.previousBtn)
        val btnNext = findViewById<Button>(R.id.nextBtn)
        val btnSubmit = findViewById<Button>(R.id.submitBtn)
        if(index == 1){
            btnNext.visibility = View.VISIBLE
        }
        else if(index == questions!!.size){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]

        question?.let {

            findViewById<TextView>(R.id.QuestionDescription).text = it.description
            val optionAdapter = OptionAdapter(this, it)
            val optionList = findViewById<RecyclerView>(R.id.optionList)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }
    }
}