package com.example.dobloc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    private var tvAgeInMonths : TextView? = null
    private var tvAgeInYears : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker: Button = findViewById(R.id.buttonDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours   =   findViewById(R.id.tvAgeInHours)
        tvAgeInDays    =   findViewById(R.id.tvAgeInDays)
        tvAgeInMonths    =   findViewById(R.id.tvAgeInMonth)
        tvAgeInYears    =   findViewById(R.id.tvAgeInYears)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDayOfMonth ->
            //Toast.makeText(this,"DatePicker Works", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            tvSelectedDate?.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

           theDate?.let{

               val selectedDateInMinutes = theDate.time /60000

               var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

               currentDate?.let{

                   val currentDateInMinutes = currentDate.time / 60000

                   val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                   tvAgeInMinutes?.text = differenceInMinutes.toString()
                   tvAgeInHours?.text =  (differenceInMinutes/60).toString()
                   tvAgeInDays?.text =  (differenceInMinutes/(60*24)).toString()
                   tvAgeInMonths?.text =  "Months: " + (differenceInMinutes/(60*24*30)).toString()
                   tvAgeInYears?.text =  "Years: " + (differenceInMinutes/(60*24*30*12)).toString()
               }

           }
                                                                             },
        year,
        month,
        day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}