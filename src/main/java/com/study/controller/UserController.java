package com.study.controller;

import com.study.dto.UserDto;
import com.study.service.UserService;
import com.study.service.impl.UserServiceImpl;
import com.study.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService service;

    @PostMapping(path="/user")
    public ResponseEntity<Void> saveUser(@RequestBody UserDto userDto){

        service.saveUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/user")
    public ResponseEntity<List<UserDto>> getAll() {

        List<UserDto> todoDtoList =  service.getAll();

        return ResponseEntity.ok(todoDtoList);
    }

}
