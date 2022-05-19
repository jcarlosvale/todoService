package com.study.service.impl;

import com.study.domain.model.UserEntity;
import com.study.domain.repository.UserRepository;
import com.study.dto.UserDto;
import com.study.exception.UserNotFoundException;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public void saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto.getUsername(), userDto.getName());
        repository.save(userEntity);
    }

    public List<UserDto> getAll() {
        return
        repository
                .findAll()
                .stream()
                .map(user -> new UserDto(user.getUsername(), user.getName()))
                .collect(Collectors.toList());
    }

    public UserEntity findUserByUsername(String username) throws UserNotFoundException{
        return repository
                .findById(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado."));
    }
}
