package com.example.abhaydayma

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

var giveColors = GiveColors()

data class MyEmpAdapter(val context: Context, val empList: ArrayList<EmployeeDetails>): Adapter<MyEmpAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : ViewHolder(itemView){
        var empName = itemView.findViewById<TextView>(R.id.empName)
        var empPhoneNumber = itemView.findViewById<TextView>(R.id.empPhoneNumber)
        var empEmail = itemView.findViewById<TextView>(R.id.empEmail)
        var empReportsTo = itemView.findViewById<TextView>(R.id.empReportsTo)
        var empUrlToImage = itemView.findViewById<TextView>(R.id.empProfileImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false))
    }

    override fun getItemCount(): Int {
       return empList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.empName.text = empList.get(position).name
        holder.empEmail.text = empList.get(position).email
        holder.empPhoneNumber.text = empList.get(position).phoneNumber
        holder.empReportsTo.text = "Reports To: ${empList.get(position).reportsTo}"
        holder.empUrlToImage.text = "Profile Picture: ${empList.get(position).urlToImage}"
        holder.itemView.setBackgroundColor(Color.parseColor(giveColors.getColors()))
    }
}