package io.github.jimiwrd.controller

import io.github.jimiwrd.error.ErrorCode
import io.github.jimiwrd.error.ErrorResponse
import io.github.jimiwrd.model.BmrResult
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class BmrControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "should return BMR and gender from BMR male endpoint" () {
        when:
        def response = client.toBlocking().retrieve(
                "/bmr/male?weight=70&height=175&age=30", BmrResult
        )

        then:
        response.bmr == 1649
        response.gender == BmrResult.Gender.MALE
    }

    def "should return BMR and gender from BMR male endpoint" () {
        when:
        def response = client.toBlocking().retrieve(
                "/bmr/female?weight=70&height=175&age=30", BmrResult
        )

        then:
        response.bmr == 1483
        response.gender == BmrResult.Gender.FEMALE
    }

    def "should return ErrorResponse when weight, height, or age <= 0, male endpoint" () {
        when:
        client.toBlocking().exchange("/bmr/male?weight=${weight}&height=${height}&age=${age}", BmrResult)

        then:
        def e = thrown(HttpClientResponseException)
        def response = e.response

        response.status() == expectedStatus

        with(response.getBody(ErrorResponse).get()) {
            errorCode == expectedErrorCode
            message == "Invalid parameters, Weight, Height, and Age must be > 0"
        }

        where:
        weight | height | age || expectedStatus         | expectedErrorCode
        0      | 175    | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 0      | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 175    | 0   || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        -1     | 175    | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | -1     | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 175    | -1  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
    }

    def "should return ErrorResponse when weight, height, or age <= 0, male endpoint" () {
        when:
        client.toBlocking().exchange("/bmr/female?weight=${weight}&height=${height}&age=${age}", BmrResult)

        then:
        def e = thrown(HttpClientResponseException)
        def response = e.response

        response.status() == expectedStatus

        with(response.getBody(ErrorResponse).get()) {
            errorCode == expectedErrorCode
            message == "Invalid parameters, Weight, Height, and Age must be > 0"
        }

        where:
        weight | height | age || expectedStatus         | expectedErrorCode
        0      | 175    | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 0      | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 175    | 0   || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        -1     | 175    | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | -1     | 30  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 175    | -1  || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
    }

}
