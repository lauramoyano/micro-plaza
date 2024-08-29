package com.pragma.restaurantcrud.infrastructure.output.client;

import com.pragma.restaurantcrud.domain.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper {
    User mapToUser(UserDto userDto);
}
