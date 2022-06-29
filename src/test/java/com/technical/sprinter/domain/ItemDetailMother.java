package com.technical.sprinter.domain;

import com.technical.sprinter.model.ItemDetails;

public class ItemDetailMother {

    public static ItemDetails random() {
        ItemDetails itemDetails = new ItemDetails();

        itemDetails.setId(LongMother.random());
        itemDetails.setCreationDate(OffsetDateTimeMother.random());
        itemDetails.setReferenceCode(StringMother.random());
        itemDetails.setName(StringMother.random());
        itemDetails.setBrand(BrandDetailMother.random());
        itemDetails.setSize(StringMother.random());
        itemDetails.setMaterial(StringMother.random());
        itemDetails.setColor(StringMother.random());
        itemDetails.setSport(StringMother.random());

        return itemDetails;
    }
}
