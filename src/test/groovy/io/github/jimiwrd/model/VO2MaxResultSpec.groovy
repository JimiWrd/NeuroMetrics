package io.github.jimiwrd.model

import spock.lang.Specification

class VO2MaxResultSpec extends Specification {

    def "should return correct category given VO2Max and age" () {
        when:
        def result = VO2MaxResult.getCategory(vo2Max, age)


        then:
        result == expectedCategory

        where:
        vo2Max | age || expectedCategory
        55     | 20  || VO2MaxResult.Category.EXCELLENT
        35     | 22  || VO2MaxResult.Category.FAIR
        44     | 33  || VO2MaxResult.Category.GOOD
        -1     | 38  || VO2MaxResult.Category.UNDEFINED
        38     | 42  || VO2MaxResult.Category.ABOVE_AVERAGE
        34.8   | 58  || VO2MaxResult.Category.AVERAGE
        0.1    | 66  || VO2MaxResult.Category.POOR
        70     | 100 || VO2MaxResult.Category.UNDEFINED
    }
}
