package com.example

import com.example.plugins.*
import com.example.services.AwardServiceImpl
import com.example.services.BetsServiceImpl
import com.example.services.LotteryServiceImpl
import com.example.services.PrizeDrawServiceImpl
import com.example.services.ResultServiceImpl
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation) { json() }

    configureBets(BetsServiceImpl())
    configureResult(ResultServiceImpl())
    configureLottery(LotteryServiceImpl())
}
