package io.github.jimiwrd.controller


import io.github.jimiwrd.error.ErrorCode
import io.github.jimiwrd.error.ErrorResponse
import io.github.jimiwrd.model.BmiResult
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class BmiControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    def "should return BMI and category from bmi metric endpoint"() {
        when:
        def response = client.toBlocking().retrieve(
                "/bmi/metric?weight=70&height=1.75", BmiResult
        )

        then:
        response.bmi == 22.86d
        response.category == BmiResult.Category.NORMAL
    }

    def "should return BMI and category from bmi imperial endpoint"() {
        when:
        def response = client.toBlocking().retrieve(
                "/bmi/imperial?weight=154&height=68", BmiResult
        )

        then:
        response.bmi == 23.41d
        response.category == BmiResult.Category.NORMAL
    }

    def "should return ErrorResponse when weight or height <= 0, metric endpoint" () {
        when:
        client.toBlocking().exchange("/bmi/metric?weight=${weight}&height=${height}", BmiResult)

        then:
        def e = thrown(HttpClientResponseException)
        def response = e.response

        response.status() == expectedStatus

        with(response.getBody(ErrorResponse).get()) {
            errorCode == expectedErrorCode
            message == "Invalid parameters given, Height and Weight must be > 0"
        }

        where:
        weight | height || expectedStatus         | expectedErrorCode
        0      | 1.75   || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | 0      || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        -1     | 1.75   || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        80     | -1     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
    }

    def "should return ErrorResponse when weight or height <= 0, imperial endpoint" () {
        when:
        client.toBlocking().exchange("/bmi/imperial?weight=${weight}&height=${height}", BmiResult)

        then:
        def e = thrown(HttpClientResponseException)
        def response = e.response

        response.status() == expectedStatus

        with(response.getBody(ErrorResponse).get()) {
            errorCode == expectedErrorCode
            message == "Invalid parameters given, Height and Weight must be > 0"
        }

        where:
        weight | height || expectedStatus         | expectedErrorCode
        0      | 68     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        154    | 0      || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        -1     | 68     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
        154    | -1     || HttpStatus.BAD_REQUEST | ErrorCode.BAD_REQUEST
    }
}
