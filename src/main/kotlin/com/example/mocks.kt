package com.example

import com.example.extension.currentDate
import com.example.extension.now
import com.example.services.Lottery
import com.example.services.Result

object Mocks {

    val lotteryOne = Lottery(name = "Pix 01", hour = "08:00")
    val lotteryTwo = Lottery(name = "Pix 01", hour = "12:00")
    val lotteryThree = Lottery(name = "Pix 01", hour = "16:00")
    val lotteryFour = Lottery(name = "Pix 01", hour = "20:00")
    val lotteryFive = Lottery(name = "Pix 01", hour = "00:00")

    val resultOne = Result(
        id = 1,
        one = "0001",
        two = "0002",
        three = "0003",
        four = "0005",
        five = "0006",
        date = now().currentDate(),
        lottery = lotteryOne
    )

    val resultTwo = Result(
        id = 1,
        one = "0002",
        two = "0003",
        three = "0004",
        four = "0005",
        five = "0006",
        date = now().currentDate(),
        lottery = lotteryTwo
    )

    val resultThree = Result(
        id = 1,
        one = "1111",
        two = "2222",
        three = "3333",
        four = "4444",
        five = "5555",
        date = now().currentDate(),
        lottery = lotteryThree
    )

    val resultFour = Result(
        id = 1,
        one = "7777",
        two = "8888",
        three = "9999",
        four = "1212",
        five = "1313",
        date = now().currentDate(),
        lottery = lotteryFour
    )

    val resultFive = Result(
        id = 1,
        one = "4545",
        two = "6767",
        three = "8787",
        four = "9009",
        five = "1324",
        date = now().currentDate(),
        lottery = lotteryFive
    )
}