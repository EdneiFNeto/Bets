package com.example.request

import com.example.exception.InvalidCPF
import com.example.exception.InvalidHour
import com.example.exception.InvalidName
import com.example.exception.InvalidNumber
import com.example.exception.InvalidRange
import com.example.util.validateHour
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class BetsRequest(
    val player: Player,
    val bets: List<Bets>
)

@Serializable
data class Player(
    val id: Long,
    val name: String,
    val cpf: String
) {
    fun validateName(): Boolean {
        if (name.isBlank()) throw InvalidName()
        return true
    }

    fun validateCPF(): Boolean {
        val regex = Regex("^(\\d{3}\\.){2}\\d{3}-\\d{2}\$")
        if (!regex.matches(cpf)) throw InvalidCPF()
        return true
    }
}

@Serializable
data class Bets(
    val type: String,
    val numbers: List<String>,
    val value: Double,
    val range: List<Int>,
    val lottery: List<Lottery>,
    private val dateTime: String = LocalDateTime.now().current()
) {
    fun validateLengthNumbers(): Boolean {
        val existsNumber = numbers.find { it.length != getType(type) }
        if(existsNumber != null) throw InvalidNumber(existsNumber)
        return true
    }

    fun validateRange(): Boolean {
        val exitRanges = range.filter { it in 1..5 }
        if(exitRanges.isEmpty()) throw InvalidRange(range.joinToString { it.toString() })
        return true
    }

    fun validateLottery(): Boolean {
        val isLotteryValid = lottery.find { it.hour.validateHour() }
        if(isLotteryValid != null) throw InvalidHour(lottery.joinToString { it.hour })
        return true
    }
}

@Serializable
data class Lottery(val name: String, val hour: String)

private fun LocalDateTime.current(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return this.format(formatter)
}

private fun getType(name: String): Int {
    return when(name.uppercase()) {
        Type.MILHAR.literal.uppercase() -> 4
        else -> 0
    }
}

enum class Type(val literal: String) {
    MILHAR("Milhar"),
    DEZENA("Centena"),
    CENTENA("Dezena"),
}