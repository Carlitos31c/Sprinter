package com.technical.sprinter.mapper;

import com.technical.sprinter.entity.ItemEntity;
import com.technical.sprinter.model.ItemDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { BrandMapper.class, DateConverter.class })
public interface ItemMapper {

    ItemDetails mapToDto(ItemEntity source);

    ItemEntity mapToEntity(ItemDetails source);

    @Mapping(target = "id", ignore = true)
    ItemEntity mapToEntitySave(ItemDetails source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    ItemDetails mapToEntityUpdate(@MappingTarget ItemDetails savedItem, ItemDetails source);
}
