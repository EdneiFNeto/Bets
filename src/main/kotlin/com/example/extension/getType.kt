package com.example.extension

import com.example.request.Type

fun String.getLengthTypeGame(): Int {
    return when (this.uppercase()) {
        Type.MILHAR.literal.uppercase() -> 4
        else -> 0
    }
}