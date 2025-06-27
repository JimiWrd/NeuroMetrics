package io.github.jimiwrd.model

import groovy.transform.builder.Builder
import io.micronaut.serde.annotation.Serdeable

@Builder
@Serdeable.Serializable
@Serdeable.Deserializable
class BmiResult{
    double bmi
    Category category

    enum Category{
        UNDERWEIGHT,
        NORMAL,
        OVERWEIGHT,
        OBESE,
        UNDEFINED
    }

    static def getCategory(double bmi) {
        switch (bmi) {
            case { it <= 18.5 && it > 0 }:
                return Category.UNDERWEIGHT
            case { it > 18.5 && it <= 24.9 }:
                return Category.NORMAL
            case { it > 24.9 && it <= 29.9 }:
                return Category.OVERWEIGHT
            case { it > 29.9 }:
                return Category.OBESE
            default:
                return Category.UNDEFINED
        }
    }
}
