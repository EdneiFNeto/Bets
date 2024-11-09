package com.example.plugins

import com.example.exception.CustomException
import com.example.request.BetsRequest
import com.example.services.BetsService
import com.example.services.PrizeDrawService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureBetsRouting(
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
                        it.validateRange()
                        it.validateLottery()
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

fun Application.configurePrizeDrawRouting(
    service: PrizeDrawService
) {
    routing {
        get("/prize_down") {
            val responses = service.drawn()
            if (responses.isEmpty()) {
                call.respond(
                    HttpStatusCode.NotFound,
                    mapOf("Error" to "Nao teve usuario sorteado!")
                )
                return@get
            }
            call.respond(HttpStatusCode.OK, responses)
        }
    }
}