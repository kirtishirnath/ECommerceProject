package com.user.service.Impl;

import com.commons.user.UserDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.SearchRequest;
import com.user.entity.User;
import com.user.mapper.UserMapper;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import com.user.service.UserUtilsService;
import com.user.validator.UserValidator;
import io.micrometer.common.util.StringUtils;
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
        return userMapper.userToUserDto(userRepository.findByUserId(userId));
    }

    @Override
    public boolean deleteById(String userId) {
        if(userRepository.existsById(userId)){
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
}
