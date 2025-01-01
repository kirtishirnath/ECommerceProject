package com.user.service.Impl;

import com.commons.product.ProductDto;
import com.commons.user.UserDto;
import com.user.mapper.UserMapper;
import com.user.repo.UserRepository;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
     private UserMapper userMapper;


    @Override
    public UserDto save(UserDto userDto) {
       return userMapper.userToUserDto(userRepository.save(userMapper.UserDtoToUser(userDto)));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getById(String userDto) {
        return null;
    }

    @Override
    public boolean deleteById(String userDto) {
        return false;
    }

    @Override
    public List<ProductDto> getAll() {
        return List.of();
    }
}
