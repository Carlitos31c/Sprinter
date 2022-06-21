package com.technical.sprinter.mapper;

import com.technical.sprinter.entity.ItemEntity;
import com.technical.sprinter.model.ItemDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDetails mapToDto(ItemEntity source);

    ItemEntity mapToEntity(ItemDetails source);
}
