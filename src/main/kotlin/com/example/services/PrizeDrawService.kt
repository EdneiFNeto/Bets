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
        return responses
    }
}