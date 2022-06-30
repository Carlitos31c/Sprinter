package com.technical.sprinter.domain;

import com.github.javafaker.Faker;

public class MotherCreator {
    public final static Faker faker = new Faker();

    public static Faker random() {
        return faker;
    }
}
