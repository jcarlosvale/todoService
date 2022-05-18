package com.study.service;

import com.study.domain.model.User;
import com.study.dto.UserDto;
import com.study.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User findUserByUsername(String username) throws UserNotFoundException;

    void saveUser(UserDto userDto);

    List<UserDto> getAll();
}
