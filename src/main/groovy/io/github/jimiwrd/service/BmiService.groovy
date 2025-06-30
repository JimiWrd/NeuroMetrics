package io.github.jimiwrd.service

import io.github.jimiwrd.model.BmiResult
import jakarta.inject.Singleton

@Singleton
class BmiService {

    def calculateBmiMetric(double weight, double height) {
        if (weight < 0 || height < 0) {
            return BmiResult.builder()
                        .bmi(0)
                        .category(BmiResult.Category.UNDEFINED)
                        .build()
        }
        double bmi = (weight / (height * height) * 100).round() / 100
        return BmiResult.builder()
                .bmi(bmi)
                .category(BmiResult.getCategory(bmi))
                .build()
    }

    def calculateBmiImperial(double weight, double height) {
        if (weight < 0 || height < 0) {
            return BmiResult.builder()
                    .bmi(0)
                    .category(BmiResult.getCategory(0))
                    .build()
        }
        double bmi = ((weight / (height * height)) * 703 * 100).round() / 100
        return BmiResult.builder()
                .bmi(bmi)
                .category(BmiResult.getCategory(bmi))
                .build()
    }

}
