package com.example.response

import com.example.request.Bets
import com.example.request.Player
import kotlinx.serialization.Serializable

@Serializable
data class BetsResponse(
    val player: Player,
    val bets:List<Bets>
)