package com.example.quizie.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quizie.R
import com.example.quizie.activities.QuestionActivity
import com.example.quizie.models.Quiz
import com.example.quizie.utils.ColorPicker
import com.example.quizie.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes: List<Quiz>): Adapter<QuizAdapter.viewHolder>() {

    class viewHolder(item: View): ViewHolder(item){
        var textViewTitle = item.findViewById<TextView>(R.id.quizTitle)
        var iconView = item.findViewById<ImageView>(R.id.quizIcon)
        var cardView: CardView = item.findViewById(R.id.cardContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false))
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.textViewTitle.text = quizzes.get(position).title
        holder.cardView.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }
}