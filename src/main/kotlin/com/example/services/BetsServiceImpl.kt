package com.example.services

import com.example.exception.extension.toBetsResponse
import com.example.request.BetsRequest
import com.example.response.BetsResponse

interface BetsService {
    fun all(): MutableList<BetsResponse>
    fun save(bets: BetsRequest)
}

class BetsServiceImpl: BetsService {
    override fun all(): MutableList<BetsResponse> {
        return list.toBetsResponse()
    }

    override fun save(bets: BetsRequest) {
        list.add(bets)
    }
}

internal var list = mutableListOf<BetsRequest>()
