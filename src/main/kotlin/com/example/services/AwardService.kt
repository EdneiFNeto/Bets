package com.example.services

import kotlinx.serialization.Serializable

internal var awards = mutableListOf<Award>()

interface AwardService {
    fun all(): MutableList<Award>
    fun save(award: Award)
}

class AwardServiceImpl: AwardService {
    override fun all(): MutableList<Award> {
        return awards
    }

    override fun save(award: Award) {
        awards.add(award)
    }
}

@Serializable
data class Award(
    val one: Boolean,
    val two: Boolean,
    val three: Boolean,
    val four: Boolean,
    val five: Boolean,
    val oneToFive: Boolean
)