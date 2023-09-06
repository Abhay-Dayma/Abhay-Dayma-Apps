package com.example.quizie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quizie.R
import com.example.quizie.models.Question

class OptionAdapter(val context: Context, val question: Question):Adapter<OptionAdapter.OptionViewHolder>(){

    private var options: List<String> = listOf(question.option1,question.option2,question.option3,question.option4 )

    inner class OptionViewHolder(optionItemView: View): ViewHolder(optionItemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        return OptionViewHolder(LayoutInflater.from(context).inflate(R.layout.option_item, parent, false))
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.optionView.setOnClickListener {
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }
        if (question.userAnswer == options[position]){
            holder.optionView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.optionView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }
}