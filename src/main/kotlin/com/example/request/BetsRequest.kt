package com.example.request

import com.example.exception.InvalidCPF
import com.example.exception.InvalidName
import com.example.exception.InvalidNumber
import com.example.services.Award
import com.example.services.Lottery
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
    val award: Award,
    val lottery: List<Lottery>,
    private val dateTime: String = LocalDateTime.now().current()
) {
    fun validateLengthNumbers(): Boolean {
        val existsNumber = numbers.find { it.length != getType(type) }
        if (existsNumber != null) throw InvalidNumber(existsNumber)
        return true
    }
}

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
    CENTENA("Dezena")
}