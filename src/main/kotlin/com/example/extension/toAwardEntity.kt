package com.example.extension

import com.example.services.Award
import com.example.services.AwardEntity

fun Award.toAwardEntity(): AwardEntity {
    return AwardEntity(
        id = 1,
        one = this.one,
        two = this.two,
        three = this.three,
        four = this.four,
        five = this.five,
        oneToFive = this.oneToFive
    )
}
