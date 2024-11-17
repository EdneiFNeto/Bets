package com.example.services

import com.example.Mocks
import kotlinx.serialization.Serializable

internal var lotterie = mutableListOf<Lottery>(
    Mocks.lotteryOne,
    Mocks.lotteryTwo,
    Mocks.lotteryThree,
    Mocks.lotteryFour,
    Mocks.lotteryFive
)

interface LotteryService {
    fun all(): MutableList<Lottery>
    fun save(lottery: Lottery)
}

class LotteryServiceImpl: LotteryService {
    override fun all(): MutableList<Lottery> {
        return lotterie
    }

    override fun save(lottery: Lottery) {
        lotterie.add(lottery)
    }
}


@Serializable
data class Lottery(val name: String, val hour: String)