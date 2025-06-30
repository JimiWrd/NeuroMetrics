package io.github.jimiwrd.model

import spock.lang.Specification

class BmiResultSpec extends Specification {

    def "should return category given bmi" () {
        when:
        def result = BmiResult.getCategory(bmi)

        then:
        result == expectedCategory

        where:
        bmi              || expectedCategory
        22.86d           || BmiResult.Category.NORMAL
        19.53d           || BmiResult.Category.NORMAL
        27.78d           || BmiResult.Category.OVERWEIGHT
        44.08d           || BmiResult.Category.OBESE
        14.69d           || BmiResult.Category.UNDERWEIGHT
        -1               || BmiResult.Category.UNDEFINED
        Double.MAX_VALUE || BmiResult.Category.OBESE
    }
}
