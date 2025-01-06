package com.user.mapper;

import com.commons.user.UserDto;
import com.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User UserDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> users);

    List<User> UserDtoListToUserList(List<UserDto> userDtos);
}
