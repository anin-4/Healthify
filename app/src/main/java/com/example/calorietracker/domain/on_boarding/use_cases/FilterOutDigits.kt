package com.example.calorietracker.domain.on_boarding.use_cases

class FilterOutDigits {

    operator fun invoke(text:String):String{
        return text.filter { it.isDigit() }
    }
}