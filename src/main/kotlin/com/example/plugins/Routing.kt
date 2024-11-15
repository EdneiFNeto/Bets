package com.example.plugins

import com.example.exception.CustomException
import com.example.request.BetsRequest
import com.example.services.Award
import com.example.services.AwardService
import com.example.services.BetsService
import com.example.services.Lottery
import com.example.services.LotteryService
import com.example.services.Result
import com.example.services.ResultService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureBets(
    service: BetsService
) {
    routing {
        post("/bets") {
            val bets = call.receive<BetsRequest>()

            try {
                bets.apply {
                    player::validateCPF
                    player::validateName

                    bets.bets.forEach {
                        it.validateLengthNumbers()
                    }
                }

                service.save(bets)
                call.respond(HttpStatusCode.OK, service.all())
            } catch (e: CustomException) {
                e.printStackTrace()
                call.respond(HttpStatusCode.Unauthorized, mapOf("Error" to e.localizedMessage))
            }
        }
    }
}

fun Application.configureResult(
    service: ResultService
) {
    routing {
        get ("/result") {
            try {
                call.respond(HttpStatusCode.OK, service.all())
            } catch (e: CustomException) {
                e.printStackTrace()
                call.respond(HttpStatusCode.Unauthorized, mapOf("Error" to e.localizedMessage))
            }
        }
    }
}

fun Application.configureLottery(
    service: LotteryService
) {
    routing {
        get ("/lottery") {
            try {
                call.respond(HttpStatusCode.OK, service.all())
            } catch (e: CustomException) {
                e.printStackTrace()
                call.respond(HttpStatusCode.Unauthorized, mapOf("Error" to e.localizedMessage))
            }
        }
    }
}

