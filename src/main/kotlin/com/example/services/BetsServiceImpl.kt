package com.example.services

import com.example.exception.extension.toBetsResponse
import com.example.extension.toAwardEntity
import com.example.request.BetsRequest
import com.example.request.Player
import com.example.response.BetsResponse
import kotlinx.serialization.Serializable

internal var bets = mutableListOf<BetsRequest>()
internal var numberEntity = mutableListOf<NumberEntity>()

interface BetsService {
    fun all(): MutableList<BetsResponse>
    fun insert(bet: BetsRequest)
}

class BetsServiceImpl : BetsService {
    override fun all(): MutableList<BetsResponse> {
        return bets.toBetsResponse()
    }

    override fun insert(bet: BetsRequest) {
        val valuePerBet = bet.bets.value / bet.bets.numbers.size
        val entities = bet.bets.numbers.flatMap { number ->
            bet.bets.lottery.map { lottery ->
                NumberEntity(
                    number = number,
                    lottery = lottery,
                    player = bet.player,
                    award = bet.bets.award.toAwardEntity(),
                    typeGame = bet.bets.type,
                    value = valuePerBet
                )
            }
        }

        numberEntity.addAll(entities)
        println("numberEntity = ${numberEntity.map { it.number to it.lottery.hour }}")
        bets.add(bet)
    }
}

data class NumberEntity(
    val number: String,
    var lottery: Lottery,
    val player: Player,
    val award: AwardEntity,
    val typeGame: String,
    val value: Double
)

@Serializable
data class AwardEntity(
    val id: Long,
    val one: Boolean,
    val two: Boolean,
    val three: Boolean,
    val four: Boolean,
    val five: Boolean,
    val oneToFive: Boolean
)

