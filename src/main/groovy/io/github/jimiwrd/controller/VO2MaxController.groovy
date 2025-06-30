package io.github.jimiwrd.controller

import io.github.jimiwrd.model.VO2MaxResult
import io.github.jimiwrd.service.VO2MaxService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/vo2max")
class VO2MaxController {

    private final VO2MaxService vo2MaxService

    VO2MaxController(VO2MaxService vo2MaxService) {
        this.vo2MaxService = vo2MaxService
    }

    @Get
    VO2MaxResult calculateVO2Max(@QueryValue int hr, @QueryValue int age) {
        return vo2MaxService.calculateVO2Max(hr, age)
    }
}
