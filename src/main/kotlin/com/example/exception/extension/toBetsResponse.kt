package com.example.exception.extension

import com.example.request.BetsRequest
import com.example.response.BetsResponse

fun List<BetsRequest>.toBetsResponse(): MutableList<BetsResponse> {
    val list = arrayListOf<BetsResponse>()
    this.forEach {
        list.add(BetsResponse(player = it.player, bets = it.bets))
    }
    return list
}