package com.user.service;

import com.commons.user.LoginDto;
import com.commons.user.LoginResponseDto;
import com.commons.user.UserDto;

public interface UserService extends GenericService<UserDto> {
    LoginResponseDto login(LoginDto loginDto);
}
