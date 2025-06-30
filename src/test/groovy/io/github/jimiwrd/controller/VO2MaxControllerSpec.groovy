package io.github.jimiwrd.controller

import io.github.jimiwrd.error.ErrorCode
import io.github.jimiwrd.error.ErrorResponse
import io.github.jimiwrd.model.BmiResult
import io.github.jimiwrd.model.VO2MaxResult
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
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

    def "should return ErrorResponse when HR or Age <= 0" () {
        when:
        client.toBlocking().exchange("/vo2max?hr=${hr}&age=${age}", VO2MaxResult)

        then:
        def e = thrown(HttpClientResponseException)
        def response = e.response

        response.status() == expectedStatus

        with(response.getBody(ErrorResponse).get()) {
            errorCode == expectedErrorCode
            message == "Invalid parameters given, HR and Age must be > 0"
        }

        where:
        hr     | age    || expectedStatus         | expectedErrorCode
        0      | 20     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        50     | 0      || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        -1     | 20     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        50     | -1     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
    }
}
