package io.github.jimiwrd.service

import io.github.jimiwrd.error.BadRequestException
import io.github.jimiwrd.model.BmrResult
import jakarta.inject.Singleton

@Singleton
class BmrService {

    def calculateBmrMale(double weight, double height, int age) {
        if(weight <= 0 || height <= 0 || age <= 0){
            throw new BadRequestException("Invalid parameters, Weight, Height, and Age must be > 0")
        }

        //using the Mifflin-St Jeor Equation

        def bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5

        def bmrRounded = bmr.round()

        return BmrResult.builder()
                .bmr(bmrRounded)
                .gender(BmrResult.Gender.MALE)
                .build()
    }

    def calculateBmrFemale(double weight, double height, int age) {
        if(weight <= 0 || height <= 0 || age <= 0){
            throw new BadRequestException("Invalid parameters, Weight, Height, and Age must be > 0")
        }

        def bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161

        def bmrRounded = bmr.round()

        return BmrResult.builder()
                .bmr(bmrRounded)
                .gender(BmrResult.Gender.FEMALE)
                .build()
    }
}
