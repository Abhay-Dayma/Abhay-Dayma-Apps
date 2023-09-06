package com.example.quizie.models

class Question{
   lateinit var description: String
    lateinit var option1: String
    lateinit var option2: String
    lateinit var option3: String
    lateinit var option4: String
    lateinit var answer: String
    var userAnswer: String = ""

    constructor(){

    }
    constructor(description:String, option1:String, option2: String, option3:String, option4:String, answer:String){
        this.description = description
        this.option1 = option1
        this.option2 = option2
        this.option4 = option4
        this.option3 = option3
        this.answer = answer
    }
}