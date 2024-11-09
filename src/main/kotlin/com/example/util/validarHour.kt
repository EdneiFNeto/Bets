package com.example.util

import java.time.LocalTime

fun String.validateHour(): Boolean {
    val hourInit = LocalTime.parse(this)
    val currentHour = LocalTime.parse(LocalTime.now().toString())
    println("currentHour = $currentHour")
    return !hourInit.isBefore(currentHour)
}

