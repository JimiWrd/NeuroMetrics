package io.github.jimiwrd.service

import io.github.jimiwrd.error.BadRequestException
import io.github.jimiwrd.model.VO2MaxResult
import jakarta.inject.Singleton

import java.math.RoundingMode


@Singleton
class VO2MaxService {

    def calculateVO2Max(int hr, int age) {
        if (hr <= 0 || age <= 0) {
            throw new BadRequestException("Invalid parameters given, HR and Age must be > 0")
        }

        def maxHr = (208 - (age * 0.7d)).round()

        def vo2Max = new BigDecimal((maxHr / hr) * 15.3d).setScale(1, RoundingMode.DOWN).doubleValue()

        return VO2MaxResult.builder()
                .vo2Max(vo2Max)
                .category(VO2MaxResult.getCategory(vo2Max, age))
                .build()
    }
}
