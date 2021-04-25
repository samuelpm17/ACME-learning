package com.unosquare.acmelearning.exception;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalCustomExceptionHandler extends ResponseEntityExceptionHandler {

    public GlobalCustomExceptionHandler() {
        super();
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(
            final ApplicationException ex,
            final WebRequest request) throws IOException {
        return handleLogAndResponse(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleApplicationException(
            final BusinessException ex,
            final WebRequest request) throws IOException {
        return handleLogAndResponse(ex, request, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> handleLogAndResponse(
            final Exception ex,
            final WebRequest request,
            final HttpStatus httpStatus) {
        log.error(ex.getMessage(), ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), httpStatus, request);
    }

}
