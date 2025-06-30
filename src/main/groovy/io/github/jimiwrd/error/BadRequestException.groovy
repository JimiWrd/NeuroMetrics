package io.github.jimiwrd.error

import io.micronaut.http.HttpStatus

class BadRequestException extends BaseException {
    BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST)
    }
}
