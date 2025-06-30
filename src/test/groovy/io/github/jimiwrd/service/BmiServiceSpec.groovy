package io.github.jimiwrd.service

import io.github.jimiwrd.model.BmiResult
import spock.lang.Specification

class BmiServiceSpec extends Specification {

    def bmiService = new BmiService()

    def "should calculate correct BMI and return category when metric"() {
        when:
        def result = bmiService.calculateBmiMetric(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        70     | 1.75   || 22.86d       | BmiResult.Category.NORMAL
        50     | 1.60   || 19.53d       | BmiResult.Category.NORMAL
        90     | 1.80   || 27.78d       | BmiResult.Category.OVERWEIGHT
        120    | 1.65   || 44.08d       | BmiResult.Category.OBESE
        45     | 1.75   || 14.69d       | BmiResult.Category.UNDERWEIGHT
    }

    def "should calculate correct BMI and return category when imperial"() {
        when:
        def result = bmiService.calculateBmiImperial(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        154    | 68     || 23.41d      | BmiResult.Category.NORMAL
        110    | 63     || 19.48d      | BmiResult.Category.NORMAL
        198    | 71     || 27.61d      | BmiResult.Category.OVERWEIGHT
        265    | 65     || 44.09d      | BmiResult.Category.OBESE
        99     | 69     || 14.62d      | BmiResult.Category.UNDERWEIGHT
    }

    def "should take negative inputs and handle it as 0, return category UNDEFINED"() {
        when:
        def result = bmiService.calculateBmiMetric(weight, height)

        then:
        result.bmi == expectedBmi
        result.category == expectedCategory

        where:
        weight | height || expectedBmi | expectedCategory
        70     | -1.75  || 0d           | BmiResult.Category.UNDEFINED
        -70    | 1.75   || 0d           | BmiResult.Category.UNDEFINED
    }
}
