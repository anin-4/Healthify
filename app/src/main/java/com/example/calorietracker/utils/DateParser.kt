package com.example.calorietracker.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun parseDateText(date: LocalDate): String {
    val today = LocalDate.now()
    return when(date) {
        today -> "Today"
        today.minusDays(1) -> "yesterday"
        today.plusDays(1) -> "Tomorrow"
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }
}