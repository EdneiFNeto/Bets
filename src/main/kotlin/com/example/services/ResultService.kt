package com.example.services

import com.example.Mocks
import kotlinx.serialization.Serializable

internal var results = mutableListOf<Result>(Mocks.result)

interface ResultService {
    fun all(): MutableList<Result>
    fun save(result: Result)
}

class ResultServiceImpl : ResultService {
    override fun all(): MutableList<Result> {
        return results
    }

    override fun save(result: Result) {
        results.add(result)
    }
}

@Serializable
data class Result(
    val id: Long,
    val one: String,
    val two: String,
    val three: String,
    val four: String,
    val five: String,
    val date: String,
    val lottery: Lottery
)