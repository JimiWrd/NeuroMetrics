package io.github.jimiwrd.error

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Produces

@Controller
class GlobalExceptionHandler {

    @Error(global = true)
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<ErrorResponse> handle(BaseException exception) {
        return HttpResponse
                .status(exception.httpStatus)
                .body(exception.toErrorResponse())
    }
}
