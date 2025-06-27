package io.github.jimiwrd.controller

import io.github.jimiwrd.model.BmiResult
import io.github.jimiwrd.service.BmiService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/bmi")
class BmiController {

    private final BmiService bmiService

    BmiController(BmiService bmiService) {
        this.bmiService = bmiService
    }

    @Get("/metric")
    BmiResult calculateBmiMetric(@QueryValue double weight, @QueryValue double height) {
        return bmiService.calculateBmiMetric(weight, height)
    }

    @Get("/imperial")
    BmiResult calculateBmiImperial(@QueryValue double weight, @QueryValue double height) {
        return bmiService.calculateBmiImperial(weight, height)
    }
}
