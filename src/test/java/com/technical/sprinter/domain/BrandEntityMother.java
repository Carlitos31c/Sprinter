package com.technical.sprinter.domain;

import com.technical.sprinter.entity.BrandEntity;

public class BrandEntityMother {

    public static BrandEntity random() {
        BrandEntity brandEntity = new BrandEntity();

        brandEntity.setId(LongMother.random());
        brandEntity.setCreationDate(LocalDateTimeMother.random());
        brandEntity.setName(StringMother.random());

        return brandEntity;
    }
}
