package io.github.jimiwrd.controller

import io.github.jimiwrd.model.BmiResult
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
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
}
