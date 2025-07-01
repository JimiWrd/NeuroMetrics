package io.github.jimiwrd.model

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import io.micronaut.serde.annotation.Serdeable

@Builder
@Serdeable
@CompileStatic
class BmrResult {
    double bmr
    Gender gender

    enum Gender {
        MALE,
        FEMALE
    }
}
