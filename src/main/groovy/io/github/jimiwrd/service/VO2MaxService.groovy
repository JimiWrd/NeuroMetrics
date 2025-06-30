package io.github.jimiwrd.service

import io.github.jimiwrd.model.VO2MaxResult
import jakarta.inject.Singleton

import java.math.RoundingMode


@Singleton
class VO2MaxService {

    def calculateVO2Max(int hr, int age) {
        if (hr < 0 || age < 0) {
            return VO2MaxResult.builder()
                    .vo2Max(0)
                    .category(VO2MaxResult.Category.UNDEFINED)
                    .build()

        }

        def maxHr = (208 - (age * 0.7d)).round()

        println(maxHr)

        def vo2Max = new BigDecimal((maxHr / hr) * 15.3d).setScale(1, RoundingMode.DOWN).doubleValue()

        println(vo2Max)

        return VO2MaxResult.builder()
                .vo2Max(vo2Max)
                .category(VO2MaxResult.getCategory(vo2Max, age))
                .build()
    }
}
