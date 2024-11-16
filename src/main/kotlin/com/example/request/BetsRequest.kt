package com.example.request

import com.example.exception.InvalidCPF
import com.example.exception.InvalidName
import com.example.exception.InvalidNumber
import com.example.extension.currentDateTime
import com.example.extension.getLengthTypeGame
import com.example.extension.now
import com.example.services.Award
import com.example.services.Lottery
import kotlinx.serialization.Serializable

@Serializable
data class BetsRequest(
    val player: Player,
    val bets: Bets
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
    private val dateTime: String = now().currentDateTime()
) {
    fun validateLengthNumbers(): Boolean {
        val existsNumber = numbers.find { it.length != type.getLengthTypeGame() }
        if (existsNumber != null) throw InvalidNumber(existsNumber)
        return true
    }
}


enum class Type(val literal: String) {
    MILHAR("Milhar"),
    DEZENA("Centena"),
    CENTENA("Dezena")
}