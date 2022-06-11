package com.example.calorietracker.utils

sealed class UIEvents{
    data class ShowSnackBar(val msg:String): UIEvents()
}
