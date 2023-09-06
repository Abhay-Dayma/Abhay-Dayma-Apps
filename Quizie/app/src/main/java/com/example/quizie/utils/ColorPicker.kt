package com.example.quizie.utils

object ColorPicker {
    val colors = arrayOf("#FF950654", "#FF042588", "#FF7E0303", "#FF017133", "#FF3ECE16","#FF0BBAB4")
    var currentIdx = 0

    fun getColor():String = colors.get((currentIdx++) % colors.size)
}