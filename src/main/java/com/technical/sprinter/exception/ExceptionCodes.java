package com.technical.sprinter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodes {

    UNEXPECTED_EXCEPTION("SP_ERR_001", "An unexpected error has occurred: %s"),
    NOT_FOUND_EXCEPTION("SP_ERR_002", "Item with id %d not found");

    private String code;
    private String message;
}
