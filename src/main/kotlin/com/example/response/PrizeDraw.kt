package com.example.response

import com.example.request.Player
import kotlinx.serialization.Serializable

@Serializable
data class PrizeDraw(
    val player: Player,
    val number: List<String>,
    val value: Double
)