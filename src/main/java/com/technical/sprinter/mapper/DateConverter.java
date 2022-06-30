package com.technical.sprinter.mapper;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface DateConverter {

    default LocalDateTime map(OffsetDateTime date){
        return date.toLocalDateTime();
    }

    default OffsetDateTime map(LocalDateTime date){
        return date.atOffset(ZoneOffset.UTC);
    }
}
