package com.user.mapper;

import com.commons.user.UserDto;
import com.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User UserDtoToUser(UserDto userDto);
}
