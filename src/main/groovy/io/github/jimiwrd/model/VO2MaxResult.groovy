package io.github.jimiwrd.model

import groovy.transform.builder.Builder
import io.micronaut.serde.annotation.Serdeable

@Builder
@Serdeable.Deserializable
@Serdeable.Serializable
class VO2MaxResult {
    double vo2Max;

    Category category;

    enum Category {
        POOR,
        FAIR,
        AVERAGE,
        ABOVE_AVERAGE,
        GOOD,
        EXCELLENT,
        UNDEFINED
    }

    static def getCategory(double vo2Max, int age) {
        String ageRange = VO2_MAX_CATEGORIES.keySet().find { range ->
                    def (minAge, maxAge) = range.tokenize("-")*.toInteger()
                    age >= minAge && age <= maxAge
        }

        def thresholds = VO2_MAX_CATEGORIES[ageRange]
        def threshold = thresholds?.find {
            vo2Max >= (double) it.min && vo2Max <= (double) it.max
        }

        return threshold?.category ?: Category.UNDEFINED
    }

    // Map of Cooper Institute VO2Max standards by age

    private static Map<String, List<Map>> VO2_MAX_CATEGORIES = [
            "20-29": [
                    [min: 52.5, max: Double.MAX_VALUE, category: Category.EXCELLENT],
                    [min: 46.5, max: 52.4, category: Category.GOOD],
                    [min: 42.5, max: 46.4, category: Category.ABOVE_AVERAGE],
                    [min: 37.0, max: 42.4, category: Category.AVERAGE],
                    [min: 33.0, max: 36.9, category: Category.FAIR],
                    [min: 0.0,  max: 32.9, category: Category.POOR]
            ],
            "30-39": [
                    [min: 49.0, max: Double.MAX_VALUE, category: Category.EXCELLENT],
                    [min: 44.0, max: 48.9, category: Category.GOOD],
                    [min: 40.5, max: 43.9, category: Category.ABOVE_AVERAGE],
                    [min: 35.5, max: 40.4, category: Category.AVERAGE],
                    [min: 31.5, max: 35.4, category: Category.FAIR],
                    [min: 0.0,  max: 31.4, category: Category.POOR]
            ],
            "40-49": [
                    [min: 45.0, max: Double.MAX_VALUE, category: Category.EXCELLENT],
                    [min: 41.0, max: 44.9, category: Category.GOOD],
                    [min: 38.0, max: 40.9, category: Category.ABOVE_AVERAGE],
                    [min: 33.0, max: 37.9, category: Category.AVERAGE],
                    [min: 30.5, max: 32.9, category: Category.FAIR],
                    [min: 0.0,  max: 30.4, category: Category.POOR]
            ],
            "50-59": [
                    [min: 42.5, max: Double.MAX_VALUE, category: Category.EXCELLENT],
                    [min: 38.0, max: 42.4, category: Category.GOOD],
                    [min: 35.0, max: 37.9, category: Category.ABOVE_AVERAGE],
                    [min: 31.5, max: 34.9, category: Category.AVERAGE],
                    [min: 28.0, max: 31.4, category: Category.FAIR],
                    [min: 0.0,  max: 27.9, category: Category.POOR]
            ],
            "60-69": [
                    [min: 39.0, max: Double.MAX_VALUE, category: Category.EXCELLENT],
                    [min: 35.5, max: 38.9, category: Category.GOOD],
                    [min: 32.0, max: 35.4, category: Category.ABOVE_AVERAGE],
                    [min: 28.5, max: 31.9, category: Category.AVERAGE],
                    [min: 25.0, max: 28.4, category: Category.FAIR],
                    [min: 0.0,  max: 24.9, category: Category.POOR]
            ]
    ]

}
