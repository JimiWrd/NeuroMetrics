package io.github.jimiwrd.service

import io.github.jimiwrd.model.VO2MaxResult
import spock.lang.Specification

class VO2MaxServiceSpec extends Specification{

    def vo2MaxService = new VO2MaxService()

    def "should return correct vo2max and category given hr and age" () {
        when:
        def result = vo2MaxService.calculateVO2Max(hr, age)


        then:
        result.vo2Max == (double) expectedVO2Max
        result.category == expectedCategory

        where:
        hr | age || expectedVO2Max || expectedCategory
        60 | 52  || 43.8           || VO2MaxResult.Category.EXCELLENT
        70 | 57  || 36.7           || VO2MaxResult.Category.ABOVE_AVERAGE
        75 | 59  || 34.0           || VO2MaxResult.Category.AVERAGE
        80 | 51  || 32.8           || VO2MaxResult.Category.AVERAGE
        85 | 54  || 30.6           || VO2MaxResult.Category.FAIR
        65 | 62  || 38.8           || VO2MaxResult.Category.GOOD
    }
}
