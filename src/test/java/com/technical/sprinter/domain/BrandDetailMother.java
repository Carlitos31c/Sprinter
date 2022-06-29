package com.technical.sprinter.domain;

import com.technical.sprinter.model.BrandDetails;

public class BrandDetailMother {

    public static BrandDetails random() {
        BrandDetails brandDetails = new BrandDetails();

        brandDetails.setId(LongMother.random());
        brandDetails.setCreationDate(OffsetDateTimeMother.random());
        brandDetails.setName(StringMother.random());

        return brandDetails;
    }
}
