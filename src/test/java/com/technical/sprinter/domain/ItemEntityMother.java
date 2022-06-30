package com.technical.sprinter.domain;

import com.technical.sprinter.entity.ItemEntity;

public class ItemEntityMother {

    public static ItemEntity random() {
        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setId(LongMother.random());
        itemEntity.setCreationDate(LocalDateTimeMother.random());
        itemEntity.setReferenceCode(StringMother.random());
        itemEntity.setName(StringMother.random());
        itemEntity.setBrand(BrandEntityMother.random());
        itemEntity.setSize(StringMother.random());
        itemEntity.setMaterial(StringMother.random());
        itemEntity.setColor(StringMother.random());
        itemEntity.setSport(StringMother.random());

        return itemEntity;
    }
}
