package io.github.jimiwrd.service

import io.github.jimiwrd.model.BmrResult
import spock.lang.Specification

class BmrServiceSpec extends Specification{

    def bmrService = new BmrService()

    def "should calculate correct BMR for male" () {
        when:
        def result = bmrService.calculateBmrMale(weight, height, age)

        then:
        result.bmr == expectedBmr
        result.gender == BmrResult.Gender.MALE

        where:
        weight | height | age | expectedBmr
        70.0   | 175.0  | 30  | 1649    // Average adult male
        85.0   | 180.0  | 25  | 1855    // Athletic build
        60.0   | 165.0  | 45  | 1411    // Lighter weight, middle age
        100.0  | 190.0  | 20  | 2093    // Tall and heavy
        45.0   | 155.0  | 18  | 1334    // Young, small build
    }

    def "should calculate correct BMR for female" () {
        when:
        def result = bmrService.calculateBmrFemale(weight, height, age)

        then:
        result.bmr == expectedBmr
        result.gender == BmrResult.Gender.FEMALE

        where:
        weight | height | age | expectedBmr
        60.0   | 165.0  | 30  | 1320    // Average adult female
        75.0   | 170.0  | 25  | 1527    // Athletic build
        55.0   | 160.0  | 45  | 1164    // Lighter weight, middle age
        85.0   | 180.0  | 20  | 1714    // Tall and heavy
        45.0   | 155.0  | 18  | 1168    // Young, small build
    }
}
