package io.github.jimiwrd.service

import io.github.jimiwrd.model.BmiResult
import spock.lang.Specification

class BmiServiceSpec extends Specification {

    def bmiService = new BmiService()

    def "should calculate correct BMI and return category as String when metric"() {
        when:
        def result = bmiService.calculateBmiMetric(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        70     | 1.75   || 22.86       | BmiResult.Category.NORMAL
        50     | 1.60   || 19.53       | BmiResult.Category.NORMAL
        90     | 1.80   || 27.78       | BmiResult.Category.OVERWEIGHT
        120    | 1.65   || 44.08       | BmiResult.Category.OBESE
        45     | 1.75   || 14.69       | BmiResult.Category.UNDERWEIGHT
    }

    def "should calculate correct BMI and return category as String when imperial"() {
        when:
        def result = bmiService.calculateBmiImperial(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        154    | 68     || 23.41      | BmiResult.Category.NORMAL
        110    | 63     || 19.48      | BmiResult.Category.NORMAL
        198    | 71     || 27.61      | BmiResult.Category.OVERWEIGHT
        265    | 65     || 44.09      | BmiResult.Category.OBESE
        99     | 69     || 14.62      | BmiResult.Category.UNDERWEIGHT
    }

    def "should take negative inputs and handle it as 0, return category UNDEFINED"() {
        when:
        def result = bmiService.calculateBmiMetric(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        70     | -1.75  || 0           | BmiResult.Category.UNDEFINED
        -70    | 1.75   || 0           | BmiResult.Category.UNDEFINED
    }
}
