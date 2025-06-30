package io.github.jimiwrd.error

import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import io.micronaut.serde.annotation.Serdeable

@CompileStatic
@Serdeable
@Builder
class ErrorResponse {
    String message
    ErrorCode errorCode
}
