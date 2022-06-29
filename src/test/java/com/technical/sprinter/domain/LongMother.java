package com.technical.sprinter.domain;

public class LongMother {

    public static Long random() {
        return MotherCreator.random().number().randomNumber();
    }
}
