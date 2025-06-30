package io.github.jimiwrd.error

import io.micronaut.http.HttpStatus

class BaseException extends RuntimeException {
    final HttpStatus httpStatus
    final ErrorCode errorCode

    BaseException(String message, HttpStatus httpStatus, ErrorCode errorCode) {
        super(message)
        this.httpStatus = httpStatus
        this.errorCode = errorCode
    }

    def toErrorResponse() {
        return ErrorResponse.builder()
                .message(message)
                .errorCode(errorCode)
                .build()
    }
}
