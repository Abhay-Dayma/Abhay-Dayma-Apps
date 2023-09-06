package com.example.abhaydaymaassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import java.util.*
var count = 1
class MyAdapter(val context: Context, val li: List<Article>):Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.imageFetched)
        var titleImage = itemView.findViewById<TextView>(R.id.imageLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return li.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(li.get(position).urlToImage).into(holder.image)
        holder.titleImage.text = "This is Image ${position+1}"
    }
}

