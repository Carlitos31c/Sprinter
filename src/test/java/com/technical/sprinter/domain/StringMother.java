package com.technical.sprinter.domain;

public class StringMother {

    public static String random() {
        return MotherCreator.random().lorem().word();
    }
}
