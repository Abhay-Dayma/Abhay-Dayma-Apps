package com.example.newscheezycodes

object ColorPicker{
    val colors =
        arrayOf("#260d41", "#16537e", "#744700", "#990000", "#274e13", "#073763", "#4c1130", "#444444", "#cc0000", "#000000", "#c90076", "#6a329f", "#16537e", "#2986cc", "#5b5b5b")
    var colorIndex = 1
    fun getColor():String{
        return colors[colorIndex++ % colors.size]
    }
}