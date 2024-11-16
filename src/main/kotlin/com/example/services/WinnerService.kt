package com.example.services

import com.example.request.Player
import kotlinx.serialization.Serializable

private const val VALUE_AWARD_MILHAR = 4000.0

interface WinnerService {
    fun all(): List<Winner>
}

class WinnerServiceImpl : WinnerService {
    override fun all(): List<Winner> {
        val result: Result = selectResult() ?: return emptyList()
        println("result: $result")

        val possibleWinner = numberEntity.filter {
            it.lottery == result.lottery && it.number == result.one
        }

        println("possibleWinner = ${possibleWinner.map { Pair(it.number, it.lottery) }}")

        if (possibleWinner.isEmpty()) return emptyList()

        return when (result.lottery.hour) {
            "08:00", "12:00", "16:00", "20:00", "00:00" -> processWinners(possibleWinner)
            else -> emptyList()
        }
    }

    private fun processWinners(possibleWinner: List<NumberEntity>): List<Winner> {
        val winners = mutableListOf<Winner>()

        for (entity in possibleWinner) {
            val winner = calculateWinner(entity)
            winner.let { winners.add(it) }
        }

        println("winners = $winners")
        return winners.distinct()
    }

    private fun calculateWinner(entity: NumberEntity): Winner {
        val totalValue = numberEntity
            .filter { it.player == entity.player }
            .sumOf { it.value }

        val numbers = numberEntity
            .filter { it.player == entity.player && it.number == entity.number }
            .map { it.number }

        println("totalValue = $totalValue")
        println("value = ${entity.value}")
        println("Calc = $totalValue * ${numbers.size} = ${(totalValue * numbers.size)}")

        return Winner(
            typeGame = entity.typeGame,
            player = entity.player,
            numbers = numbers,
            value = totalValue,
            receiveValue = (totalValue * numbers.size) * VALUE_AWARD_MILHAR
        )
    }

    private fun selectResult(): Result? = results.find { it.lottery.hour == "08:00" }
}


@Serializable
data class Winner(
    val typeGame: String,
    val player: Player,
    val numbers: List<String>,
    val receiveValue: Double,
    val value: Double
)