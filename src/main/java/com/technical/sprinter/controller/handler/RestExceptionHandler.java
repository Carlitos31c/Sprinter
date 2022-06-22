package com.technical.sprinter.controller.handler;

import com.technical.sprinter.exception.ExceptionCodes;
import com.technical.sprinter.exception.NotFoundException;
import com.technical.sprinter.exception.SprinterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        log.error("Not found exception: ", ex);

        return new ResponseEntity<>(new SprinterException(ExceptionCodes.NOT_FOUND_EXCEPTION.getCode(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericExceptionHandler(Exception ex) {
        log.error("Unexpected exception: ", ex);

        return new ResponseEntity<>(new SprinterException(ExceptionCodes.UNEXPECTED_EXCEPTION.getCode(),
                String.format(ExceptionCodes.UNEXPECTED_EXCEPTION.getMessage(), ex.getCause().getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}