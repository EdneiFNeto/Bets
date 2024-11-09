package com.example.services

import com.example.response.PrizeDraw

interface PrizeDrawService {
    fun drawn(): List<PrizeDraw>
}

class PrizeDrawServiceImpl : PrizeDrawService {

    private val total = 2000.0
    private val numberDrawer = "1234"
    private val responses = mutableListOf<PrizeDraw>()

    override fun drawn(): List<PrizeDraw> {

        list.onEach { it.bets }
            .onEach { response ->
                response.bets.onEach { bets ->
                    val isExists = bets.numbers.filter { it == numberDrawer }.distinct()
                    println("isExists = $isExists")

                    if (isExists.isNotEmpty()) {
                        val index = isExists.indexOf(numberDrawer)
                        val numbers = listOf(isExists[index])

                        responses.add(
                            PrizeDraw(
                                player = response.player,
                                number = numbers,
                                value = (bets.value / bets.numbers.size) * total
                            )
                        )
                    }
                }
            }
        return responses
    }
}