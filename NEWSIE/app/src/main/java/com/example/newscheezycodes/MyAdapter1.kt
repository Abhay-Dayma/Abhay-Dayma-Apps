package com.example.newscheezycodes

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
var idx=0
fun getCol():Int{
    var colorArr = arrayListOf<Int>(Color.parseColor("#570101"),Color.parseColor("#041259"),Color.parseColor("#2C0157"),Color.parseColor("#514702"),Color.parseColor("#036017"),Color.parseColor("#05404A"))

    return colorArr.get(idx++ % colorArr.size)
}


class MyAdapter1(val context: Context, val objects: Vector<NewsKind>) : RecyclerView.Adapter<MyAdapter1.MyViewHolder1>() {
  class MyViewHolder1(itemView :View) :RecyclerView.ViewHolder(itemView){
    var newskind = itemView.findViewById<ImageView>(R.id.newsKind)
      var newstype = itemView.findViewById<TextView>(R.id.newsName)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder1 {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.newskinditem, parent, false)
        return MyViewHolder1(view)
    }
    override fun getItemCount(): Int {
        return objects.size
    }
    override fun onBindViewHolder(holder: MyViewHolder1, position: Int) {
        holder.newstype.text = objects.get(position).titletype
        holder.itemView.setBackgroundColor(getCol())
        holder.newskind.setImageResource(objects.get(position).imgsrc)
        holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView.context, SelectedNews::class.java)
            intent.putExtra("ref", holder.newstype.text)
            context.startActivity(intent)

        }
    }
}