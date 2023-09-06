package com.example.abhaydayma

class GiveColors {
    var listColors = listOf<String>("#FF8E41ED", "#FFED41C2", "#FFEA8E1E", "#FF8CD812", "#FF12D8A0")
    var random = 0
     fun getColors(): String{
        return listColors.get(random++ % listColors.size)
    }
}