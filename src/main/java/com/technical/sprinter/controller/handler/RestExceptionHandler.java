package com.technical.sprinter.controller.handler;

import com.technical.sprinter.exception.ConflictException;
import com.technical.sprinter.exception.ExceptionCodes;
import com.technical.sprinter.exception.NotFoundException;
import com.technical.sprinter.exception.SprinterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SprinterException notFoundExceptionHandler(NotFoundException ex) {
        log.error("Not found exception: ", ex);
        return new SprinterException(ExceptionCodes.NOT_FOUND_EXCEPTION.getCode(), ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public SprinterException conflictExceptionHandler(ConflictException ex) {
        log.error("Conflict exception: ", ex);
        return new SprinterException(ExceptionCodes.CONFLICT_EXCEPTION.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SprinterException genericExceptionHandler(Exception ex) {
        log.error("Unexpected exception: ", ex);

        return new SprinterException(ExceptionCodes.UNEXPECTED_EXCEPTION.getCode(),
                String.format(ExceptionCodes.UNEXPECTED_EXCEPTION.getMessage(), ex.getMessage()));
    }
}
