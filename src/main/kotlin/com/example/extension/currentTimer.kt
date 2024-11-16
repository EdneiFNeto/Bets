package com.example.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.currentDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return this.format(formatter)
}

fun LocalDateTime.currentDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}

fun LocalDateTime.currentTime(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return this.format(formatter)
}

fun now(): LocalDateTime = LocalDateTime.now()

