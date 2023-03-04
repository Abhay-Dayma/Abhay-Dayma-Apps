package com.example.mycalci

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var tvInput : TextView? = null
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }


    fun OnDelete(view: View){

        var index1 = 0
        tvInput?.text?.let { if(it.length>0) index1 = it.length-1 }

        if(tvInput?.text?.length !=0 && (tvInput?.text?.get(index1) == 'y' || tvInput?.text?.get(index1) == 'N')){
            OnClear(view)
        }

        var index = -1
        tvInput?.text?.let { if(it.length>0) index = it.length-1 }
        if(index>=0){
            if(tvInput?.text?.get(index)==' '){
                tvInput?.text = tvInput?.text?.substring(0,index-2)
            }
            else{
                if(tvInput?.text?.get(index)=='.'){
                    lastDot= false
                }
                tvInput?.text = tvInput?.text?.substring(0,index)
            }
        }
        else{
            tvInput?.text =""
            Toast.makeText(this, "Happy Calculation", Toast.LENGTH_SHORT).show()
        }
    }

    fun ondigit(view: View){
        var index = 0
        tvInput?.text?.let { if(it.length>0) index = it.length-1 }

        if((view as Button).text.equals("/") || (view as Button).text.equals("x") || (view as Button).text.equals("+") || (view as Button).text.equals("-")){
            if((view as Button).text.equals("-")  && ((tvInput?.text?.length == 0) || (tvInput?.text?.get(index) == ' ') && (tvInput?.text?.get(index) != '-'))){
                tvInput?.append((view as Button).text)
                }

            else if(tvInput?.text?.length == 0){
                tvInput?.text = ""
                Toast.makeText(this, "Please Enter Valid Expression", Toast.LENGTH_SHORT).show()
            }

            else if(tvInput?.text?.get(index) != ' ' && (tvInput?.text?.get(index) != '-')) {
                tvInput?.append(" ")
                tvInput?.append((view as Button).text)
                tvInput?.append(" ")
                lastDot = false
            }
            else {
                Toast.makeText(this, "Please Enter Valid Expression", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            if(tvInput?.text?.length !=0 && (tvInput?.text?.get(index) != 'y' && tvInput?.text?.get(index) != 'N')){
                tvInput?.append((view as Button).text)
            }
            else if(tvInput?.text?.length ==0){
                tvInput?.append((view as Button).text)
            }
            else {
                Toast.makeText(this, "Cannot extend Infinity!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun OnClear(view: View){
        tvInput?.text =""
        lastDot= false
    }

    fun OnDot(view: View){
        var index = 0
        tvInput?.text?.let { if(it.length>0) index = it.length-1 }
        if(tvInput?.text?.length !=0 && (tvInput?.text?.get(index) == 'y' || tvInput?.text?.get(index) == 'N')){
            lastDot = true
            Toast.makeText(this, "Cannot float Infinity.", Toast.LENGTH_SHORT).show()
        }
        if(!lastDot){
            tvInput?.append(".")
            lastDot=true
        }
    }

    fun calculate(str:String):Double{
        //DMAS
        var remainingStr:String

        try {
            if (str.contains('/')) {
                var n = str.indexOf('/')
                var value1 = 0.0
                var value2 = 0.0
                var start1 = n - 2
                var start2 = n + 2
                while (str.get(start1) != ' ') {
                    start1--
                    if (start1 == -1) break
                }

                while (str.get(start2) != ' ') {
                    start2++
                    if (start2 == str.length) break
                }


                value1 = str.substring(start1 + 1, n - 1).toDouble()
                value2 = str.substring(n + 2, start2).toDouble()
                var result = value1 / value2
                remainingStr = str.substring(0, start1 + 1) + result.toString() + str.substring(
                    start2,
                    str.length
                )
                //Toast.makeText(this, start1.toString()+" and " + start2.toString(), Toast.LENGTH_LONG).show()

//            if(remainingStr.contains('/') || remainingStr.contains('*') || remainingStr.contains('+') || remainingStr.contains('-')){
//                result = calculate(remainingStr)
//            }

                //return result.toDouble()
                return calculate(remainingStr)
            } else if (str.contains('x')) {
                var n = str.indexOf('x')
                var value1 = 0.0
                var value2 = 0.0
                var start1 = n - 2
                var start2 = n + 2
                while (str.get(start1) != ' ') {
                    start1--
                    if (start1 == -1) break
                }

                while (str.get(start2) != ' ') {
                    start2++
                    if (start2 == str.length) break
                }


                value1 = str.substring(start1 + 1, n - 1).toDouble()
                value2 = str.substring(n + 2, start2).toDouble()
                var result = value1 * value2
                remainingStr = str.substring(0, start1 + 1) + result.toString() + str.substring(
                    start2,
                    str.length
                )
                //Toast.makeText(this, start1.toString()+" and " + start2.toString(), Toast.LENGTH_LONG).show()

//            if(remainingStr.contains('/') || remainingStr.contains('*') || remainingStr.contains('+') || remainingStr.contains('-')){
//                result = calculate(remainingStr)
//            }

                //return result.toDouble()
                return calculate(remainingStr)
            } else if (str.contains('+')) {
                var n = str.indexOf('+')
                var value1 = 0.0
                var value2 = 0.0
                var start1 = n - 2
                var start2 = n + 2
                while (str.get(start1) != ' ') {
                    start1--
                    if (start1 == -1) break
                }

                while (str.get(start2) != ' ') {
                    start2++
                    if (start2 == str.length) break
                }


                value1 = str.substring(start1 + 1, n - 1).toDouble()
                value2 = str.substring(n + 2, start2).toDouble()
                var result = value1 + value2
                remainingStr = str.substring(0, start1 + 1) + result.toString() + str.substring(
                    start2,
                    str.length
                )
                //Toast.makeText(this, start1.toString()+" and " + start2.toString(), Toast.LENGTH_LONG).show()

//            if(remainingStr.contains('/') || remainingStr.contains('*') || remainingStr.contains('+') || remainingStr.contains('-')){
//                result = calculate(remainingStr)
//            }

                //return result.toDouble()
                return calculate(remainingStr)
            } //else if (str.contains(" - ") && str.get(str.indexOf(" - ") + 1) == ' ') {
            else if (str.contains(" - ")){
            var n = str.indexOf("- ")

                var value1 = 0.0
                var value2 = 0.0
                var start1 = n - 2
                var start2 = n + 2
                while (str.get(start1) != ' ') {
                    start1--
                    if (start1 == -1) break
                }

                while (str.get(start2) != ' ') {
                    start2++
                    if (start2 == str.length) break
                }


                value1 = str.substring(start1 + 1, n - 1).toDouble()

                if(n + 2 == str.length){
                    tvInput?.text ="Syntax Error"
                    return 0.0
                }
                value2 = str.substring(n + 2, start2).toDouble()
                var result = value1 - value2
                remainingStr = str.substring(0, start1 + 1) + result.toString() + str.substring(
                    start2,
                    str.length
                )
                //Toast.makeText(this, start1.toString()+" and " + start2.toString(), Toast.LENGTH_LONG).show()

                return calculate(remainingStr)
            }
        }catch(e:Exception){
            tvInput?.text ="Syntax Error"
        }
        return str.toDouble()
    }

    fun OnEqual(view: View){
        if(tvInput?.text?.contains(" + ") == true || tvInput?.text?.contains(" - ") == true || tvInput?.text?.contains(" x ") == true || tvInput?.text?.contains(" / ") == true ) {
            var result = 0.0
            tvInput?.text?.let { result = calculate(it.toString()) }
            if (result - Math.floor(result) == 0.00) {
                tvInput?.text = (result.toLong()).toString()
            } else {
                tvInput?.text = result.toString()
            }
        }
    }
}
