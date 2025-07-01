package io.github.jimiwrd.service

import io.github.jimiwrd.error.BadRequestException
import io.github.jimiwrd.model.VO2MaxResult
import jakarta.inject.Singleton

@Singleton
class VO2MaxService {

    def calculateVO2Max(int hr, int age) {
        if (hr <= 0 || age <= 0) {
            throw new BadRequestException("Invalid parameters given, HR and Age must be > 0")
        }

        def maxHr = 208 - (age * 0.7d)

        def vo2Max = (maxHr / hr) * 15.3d

        def vo2MaxRounded = vo2Max.round(1)

        return VO2MaxResult.builder()
                .vo2Max(vo2MaxRounded)
                .category(VO2MaxResult.getCategory(vo2MaxRounded, age))
                .build()
    }
}
