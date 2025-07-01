package io.github.jimiwrd.controller

import io.github.jimiwrd.model.BmrResult
import io.github.jimiwrd.service.BmrService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/bmr")
class BmrController {

    private final BmrService bmrService

    BmrController(BmrService bmrService) {
        this.bmrService = bmrService
    }

    @Get("/male")
    BmrResult calculateBmrMale(@QueryValue double weight, double height, int age) {
        return bmrService.calculateBmrMale(weight, height, age)
    }

    @Get("/female")
    BmrResult calculateBmrFemale(@QueryValue double weight, double height, int age) {
        return bmrService.calculateBmrFemale(weight, height, age)
    }

}
