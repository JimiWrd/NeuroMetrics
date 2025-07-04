package io.github.jimiwrd.service

import io.github.jimiwrd.error.BadRequestException
import io.github.jimiwrd.model.BmiResult
import jakarta.inject.Singleton

@Singleton
class BmiService {

    def calculateBmiMetric(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new BadRequestException("Invalid parameters given, Height and Weight must be > 0")
        }

        double bmi = weight / (height * height)

        def bmiRounded = bmi.round(2)

        return BmiResult.builder()
                .bmi(bmiRounded)
                .category(BmiResult.getCategory(bmiRounded))
                .build()
    }

    def calculateBmiImperial(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new BadRequestException("Invalid parameters given, Height and Weight must be > 0")
        }
        double bmi = ((weight / (height * height)) * 703 * 100).round() / 100
        return BmiResult.builder()
                .bmi(bmi)
                .category(BmiResult.getCategory(bmi))
                .build()
    }

}
