package com.user.controller;

import com.commons.constants.GenericApiConstants;
import com.commons.constants.GenericConstants;
import com.commons.user.UserDto;
import com.commons.utility.PageableResponse;
import com.commons.utility.ResponseDto;
import com.commons.utility.SearchRequest;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    ResponseDto saveUser(@RequestBody UserDto userDto){
        UserDto save = userService.save(userDto);
        if(null != save){
            return new ResponseDto(true,save,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY,"added"));
        }
        return new ResponseDto(true,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION,"added"));
    }

    @PutMapping
    ResponseDto update(@RequestBody UserDto userDto){
        UserDto updated = userService.update(userDto);
        if(null != updated){
            return new ResponseDto(true,updated, String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY,"updated"));
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "update"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto getById(@RequestParam(name = "userId") String userId){
        UserDto userDto = userService.getById(userId);
        if(null != userDto){
            return new ResponseDto(true,userDto,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "fetched") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchById"));
    }

    @DeleteMapping(GenericApiConstants.ENDPOINT_BY_ID)
    ResponseDto deleteById(@RequestParam(name = "userId") String userId){
        boolean success = userService.deleteById(userId);
        if(success){
            return new ResponseDto(true,userId,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "deleted") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "deleteById"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_GET_ALL)
    ResponseDto getAll(){
        List<UserDto> userDtoList = userService.getAll();
        if(!CollectionUtils.isEmpty(userDtoList)){
            return new ResponseDto(true,userDtoList,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "retrived") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchAll"));
    }

    @GetMapping(GenericApiConstants.ENDPOINT_GET_ALL_BY_FILTER)
    ResponseDto search(SearchRequest searchRequest){
        PageableResponse<UserDto> userDtos = userService.search(searchRequest);
        if(null != userDtos){
            return new ResponseDto(true,userDtos,String.format(GenericConstants.OPERATION_PERFORMED_SUCCESSFULLY, "retrived") );
        }
        return new ResponseDto(false,null,String.format(GenericConstants.ERROR_WHILE_PERFORMING_OPERATION, "fetchAllPageable"));
    }

}
