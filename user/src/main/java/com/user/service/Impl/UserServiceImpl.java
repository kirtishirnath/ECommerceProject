package com.user.service.Impl;

import com.commons.user.LoginDto;
import com.commons.user.LoginResponseDto;
import com.commons.user.UserDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.SearchRequest;
import com.user.entity.User;
import com.user.mapper.UserMapper;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import com.user.service.UserUtilsService;
import com.user.validator.UserValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
     private UserMapper userMapper;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserUtilsService userUtilsService;

    @Override
    public UserDto save(UserDto userDto) {
        if(userDto != null){
            userValidator.validate(userDto,false);
            return userMapper.userToUserDto(userRepository.save(userMapper.UserDtoToUser(userDto)));
        }
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        if(userDto != null){
            userValidator.validate(userDto,true);
            User existingUser = userRepository.findByUserId(userDto.getUserId());
            BeanUtils.copyProperties(userDto,existingUser);
            return userMapper.userToUserDto(userRepository.save(existingUser));
        }
        return null;
    }

    @Override
    public UserDto getById(String userId) {
        if(!StringUtils.isBlank(userId))
             return userMapper.userToUserDto(userRepository.findByUserId(userId));
        return null;
    }

    @Override
    public boolean deleteById(String userId) {
        if(!StringUtils.isBlank(userId) && userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.userListToUserDtoList(userRepository.findAll());
    }

    @Override
    public PageableResponse<UserDto> search(SearchRequest searchRequest) {
        PageRequest pageRequest = userUtilsService.preparePageableRequest(searchRequest);
        String filter = !StringUtils.isBlank(searchRequest.getFilter()) ? searchRequest.getFilter() : Strings.EMPTY;
        return buildPageableResponse(userRepository.findAllUsers(filter.toUpperCase(),pageRequest));
    }

    private PageableResponse<UserDto> buildPageableResponse(Page<User> allUsers) {
        PageableResponse pageableResponse = new PageableResponse();
        pageableResponse.setCurrentPage(allUsers.getNumber());
        pageableResponse.setTotalPages(allUsers.getTotalPages());
        pageableResponse.setTotalRecord(allUsers.getTotalElements());
        pageableResponse.setData(userMapper.userListToUserDtoList(allUsers.getContent()));
        return pageableResponse;
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        if(loginDto != null){
            if(!StringUtils.isAnyBlank(loginDto.getPassword(),loginDto.getUserName())) {
                User existingUser = userRepository.findByUsernameAndPassword(loginDto.getUserName(), loginDto.getPassword());
                if (existingUser != null) {
                    return setLoginResponse(existingUser.getFirstName() + " "+ existingUser.getLastName(), 200, existingUser.getRole(),"User logged in successfully.");
                } else {
                    return setLoginResponse(null, 500, null,"User does not exists for given username and password.");
                }
            }
            return setLoginResponse(null,500,null,"Username or password cannot be empty.");
        }
        return null;
    }

    private LoginResponseDto setLoginResponse(String displayName ,Integer status,String role,String message) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setDisplayName(displayName);
        loginResponseDto.setStatus(Integer.toString(status));
        loginResponseDto.setRole(role);
        loginResponseDto.setMessage(message);
        return loginResponseDto;
    }
}
