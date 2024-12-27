package com.user.controller;

import com.commons.constants.GenericConstants;
import com.commons.user.UserDto;
import com.commons.utility.ResponseDto;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    ResponseDto saveUser( @RequestBody UserDto userDto){
        UserDto save = userService.save(userDto);
        if(null != save){
            return new ResponseDto(true,save,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY,"added"));
        }
        return new ResponseDto(true,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION,"added"));
    }


}
