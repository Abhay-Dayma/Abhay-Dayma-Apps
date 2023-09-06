package com.example.newscheezycodes

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Vector

class MyAdapter2(val context: Context, val objects :Vector<CountryInfo>):RecyclerView.Adapter<MyAdapter2.MyViewHolder2>(){

    class MyViewHolder2(itemView: View):RecyclerView.ViewHolder(itemView){
        val flagView = itemView.findViewById<ImageView>(R.id.globalNewsImage)
        val countryName = itemView.findViewById<TextView>(R.id.globalCountryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.globalnews, parent, false)
        return MyAdapter2.MyViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        holder.flagView.setImageResource(objects.get(position).countryFlag)
        holder.itemView.setBackgroundColor(getCol())
        holder.countryName.text = objects.get(position).countryName
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SelectedNews::class.java)
            intent.putExtra("country", HMap().getCountryCode(objects.get(position).countryName))
            intent.putExtra("status", "true")
            context.startActivity(intent)
        }
    }
}