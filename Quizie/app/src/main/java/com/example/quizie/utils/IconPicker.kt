package com.example.quizie.utils

import com.example.quizie.R

object IconPicker {
    val Icons = arrayOf(
        R.drawable.ed1,
        R.drawable.ed2,
        R.drawable.ed3,
        R.drawable.ed4,
        R.drawable.ed5,
        R.drawable.ed6,
        R.drawable.ed7,
        R.drawable.ed8,
        R.drawable.ed9,
        R.drawable.ed10
    )
    var currentIdx = 0
    fun getIcon():Int = Icons.get((currentIdx++) % Icons.size)
}