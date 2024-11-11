package com.example.response

import com.example.request.Player
import kotlinx.serialization.Serializable

@Serializable
data class PrizeDraw(
    val player: Player,
    val number: List<String>,
    val value: Double
)

/**
 * ***********************************************
 *  Regras para o ganhador
 * ***********************************************

 *  Resultado cadastrado

 *  1 - premio: 1234
 *  2 - premio: 1122
 *  3 - premio: 3344
 *  4 - premio: 5566
 *  5 - premio: 7788

 *  Numeros apostados:
 *  {
 *      player: {
 *          id: 1,
 *          nome: Jose,
 *          cpf: 000.000.000-00
 *      },
 *      number: [
 *          {
 *              number: 0001,
 *              playerId: 1,
 *              awardId: 1
 *          }
 *      ],
 *      award: [1],
 *      valor: 1.00,
 *      playerId: 1
 *  }
 *
 *  Buscar vencedores no primerio premio
 *
 */