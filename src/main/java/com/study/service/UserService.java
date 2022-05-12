package com.study.service;

import com.study.domain.model.User;
import com.study.domain.repository.UserRepository;
import com.study.dto.UserDto;
import com.study.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getName());
        repository.save(user);
    }

    public List<UserDto> getAll() {
        return
        repository
                .findAll()
                .stream()
                .map(user -> new UserDto(user.getUsername(), user.getName()))
                .collect(Collectors.toList());
    }

    public User findUserByUsername(String username) throws UserNotFoundException{
        return repository
                .findById(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario nao encontrado."));
    }
}
