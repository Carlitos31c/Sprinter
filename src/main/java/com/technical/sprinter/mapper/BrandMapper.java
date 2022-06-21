package com.technical.sprinter.mapper;

import com.technical.sprinter.entity.BrandEntity;
import com.technical.sprinter.model.BrandDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDetails mapToDto(BrandEntity source);

    BrandEntity mapToEntity(BrandDetails source);
}
