package io.github.jimiwrd.controller

import io.github.jimiwrd.model.VO2MaxResult
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class VO2MaxControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "should return VO2Max and Category for VO2Max endpoint" () {
        when:
        def response = client.toBlocking().retrieve(
                "/vo2max?hr=${hr}&age=${age}", VO2MaxResult
        )

        then:
        response.vo2Max == expectedVO2Max
        response.category == expectedCategory

        where:
        hr | age || expectedVO2Max || expectedCategory
        60 | 52  || 43.8           || VO2MaxResult.Category.EXCELLENT
        70 | 57  || 36.7           || VO2MaxResult.Category.ABOVE_AVERAGE
        -1 | 55  || 0              || VO2MaxResult.Category.UNDEFINED
    }
}
