package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.PinMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMessageMapper {

        @Mapping(target = "pin", source = "pin")
        MessageRequestDto messageSmsToMessageSmsRequestDto(PinMessage messageSms);

}
