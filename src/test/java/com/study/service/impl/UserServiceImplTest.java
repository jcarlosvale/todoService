package com.study.service.impl;

import com.study.domain.model.UserEntity;
import com.study.domain.repository.UserRepository;
import com.study.dto.UserDto;
import com.study.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserEntity userEntity;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;


    @Test
    void getAllOk() {
        //GIVEN
        UserEntity user1 = UserEntity.builder().username("user1").name("name1").build();
        UserEntity user2 = UserEntity.builder().username("user2").name("name2").build();
        List<UserEntity> returnList = List.of(user1, user2);


        UserDto dto1 = UserDto.builder().username("user1").name("name1").build();
        UserDto dto2 = UserDto.builder().username("user2").name("name2").build();
        List<UserDto> expected = List.of(dto1, dto2);

        given(repository.findAll()).willReturn(returnList);

        //WHEN
        var actual = service.getAll();

        //THEN
        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void getAllEmpty() {
        //GIVEN
        given(repository.findAll()).willReturn(List.of());

        //WHEN
        var actual = service.getAll();

        //THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void findUserByUsername_UserNotFound() {
        //GIVEN
        String username = "some username";

        given(repository.findById(username)).willReturn(Optional.empty());

        //WHEN THEN
        assertThatThrownBy(() -> service.findUserByUsername(username))
                .isInstanceOf(UserNotFoundException.class)
                .message().isEqualTo("Usuario nao encontrado.");
    }

    @Test
    void findUserByUsername() {
        //GIVEN
        String username = "some username";

        given(repository.findById(username)).willReturn(Optional.of(userEntity));

        //WHEN
        var actual = service.findUserByUsername(username);

        // THEN
        assertThat(actual).isEqualTo(userEntity);
    }

    @Test
    void saveUser() {
        //GIVEN
        UserDto dto = UserDto.builder().username("user1").name("name1").build();
        UserEntity entity = UserEntity.builder().username("user1").name("name1").build();

        //WHEN
        service.saveUser(dto);

        //THEN
        verify(repository, times(1)).save(entity);
    }

    @Test
    void saveUser_dtoNull() {
        //GIVEN
        UserDto dto = null;

        //WHEN
        assertThatThrownBy(() -> service.saveUser(dto))
                .isInstanceOf(NullPointerException.class);

        //THEN
        verify(repository, never()).save(any(UserEntity.class));
    }


}