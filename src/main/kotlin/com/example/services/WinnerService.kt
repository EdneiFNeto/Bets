package com.example.services

import com.example.request.Player
import kotlinx.serialization.Serializable

private const val VALUE_AWARD_MILHAR = 4000.0

interface WinnerService {
    fun all(hour: String): List<Winner>
}

class WinnerServiceImpl : WinnerService {

    override fun all(hour: String): List<Winner> {

        val result = selectResult(hour) ?: return emptyList()
        println("result: $result")

        val possibleWinners = listOf(
            result.one,
            result.two,
            result.three,
            result.four,
            result.five
        ).map { number ->
            findWinnerForNumber(result.lottery, number)
        }

        println("possibleWinners: $possibleWinners")

        if (possibleWinners.isEmpty()) return emptyList()

        return when (result.lottery.hour) {
            "08:00", "12:00", "16:00", "20:00", "00:00" -> possibleWinners.flatMap {
                processWinners(
                    it
                )
            }

            else -> emptyList()
        }
    }

    private fun findWinnerForNumber(lottery: Lottery, number: String): List<NumberEntity> {
        return numberEntity.filter { it.lottery == lottery && it.number == number }
    }

    private fun processWinners(possibleWinner: List<NumberEntity>): List<Winner> {
        return possibleWinner.distinct().map { entity ->
            calculateWinner(entity)
        }
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
            receiveValue = (totalValue * numbers.size) * VALUE_AWARD_MILHAR,
            award = listOf()
        )
    }

    private fun selectResult(hour: String) = results.find { it.lottery.hour == hour }
}

@Serializable
data class Winner(
    val typeGame: String,
    val player: Player,
    val numbers: List<String>,
    val receiveValue: Double,
    val value: Double,
    val award: List<String>
)

@Serializable
data class WinnerRequest(val hour: String)
