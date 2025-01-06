package com.user.validator;

import com.commons.exception.BaseException;
import com.commons.user.UserDto;
import com.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Service
public class UserValidator {

    @Autowired
    UserRepository userRepository;

    public void validate(UserDto userDto,boolean isUpdate) {
        validateIfExists(userDto,isUpdate);
        validateUser(userDto);
        validatePhoneNumber(userDto);
    }

    private void validatePhoneNumber(UserDto userDto) {
        if(!StringUtils.isBlank(userDto.getPhoneNumber()) && userDto.getPhoneNumber().length() != 10)
            throw new BaseException("Phone number must of size 10.");

        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            throw new BaseException("Entered duplicate phone number.");

    }

    private void validateIfExists(UserDto userDto,boolean isUpdate) {
        if(!isUpdate && !StringUtils.isBlank(userDto.getUserId()) && userRepository.existsById(userDto.getUserId())){
            throw new BaseException("User already exists.");
        } if(isUpdate && (StringUtils.isBlank(userDto.getUserId()) || !userRepository.existsById(userDto.getUserId()))){
            throw new BaseException("User does not already exist.");
        }
    }

    private void validateUser(UserDto userDto) {
        if(StringUtils.isAnyBlank(userDto.getUserName(),userDto.getPassword())){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR,"Username or password cannot be empty.");
        }
        if(StringUtils.isBlank(userDto.getFirstName())){
            throw new BaseException("Please enter first name.");
        }
    }

}
