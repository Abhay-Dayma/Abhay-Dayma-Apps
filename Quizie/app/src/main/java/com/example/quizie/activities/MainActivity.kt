package com.example.quizie.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizie.R
import com.example.quizie.R.id.btnDatePicker
import com.example.quizie.R.id.quizRecyclerView
import com.example.quizie.adapter.QuizAdapter
import com.example.quizie.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
    }
    private fun setUpViews(){
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerView()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        findViewById<FloatingActionButton>(btnDatePicker).setOnClickListener(){
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DatePicker", datePicker.headerText)
                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))
                startActivity(Intent(this, QuestionActivity::class.java).putExtra("DATE", date))
            }
            datePicker.addOnNegativeButtonClickListener {

            }
            datePicker.addOnCancelListener{

            }
        }
    }


    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            Log.d("Data", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        //for(i in 0 until 11) quizList.add(Quiz("$i", "$i"))
        adapter = QuizAdapter(this, quizList)
        findViewById<RecyclerView>(quizRecyclerView).layoutManager = GridLayoutManager(this, 2)
        findViewById<RecyclerView>(quizRecyclerView).adapter = adapter
    }

    private fun setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.appBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(this, findViewById(R.id.drawerID),
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()

        findViewById<NavigationView>(R.id.navigationView).setNavigationItemSelectedListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            findViewById<DrawerLayout>(R.id.drawerID).closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }
}