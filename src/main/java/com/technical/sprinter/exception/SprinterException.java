package com.technical.sprinter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SprinterException implements Serializable {

    private String code;

    private String message;
}
