package com.study.controller;

import com.study.dto.UserDto;
import com.study.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService service;

    @Mock
    private UserDto dto;

    @InjectMocks
    private UserController controller;

    @Test
    void saveUser() {
        //given

        //when
        var actual = controller.saveUser(dto);

        //then
        verify(service, times (1)).saveUser(dto);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(actual.getBody()).isNull();
    }

    @Test
    void getAll(){
        //GIVEN
        List<UserDto> list = List.of(dto);
        given(service.getAll()).willReturn(list);

        //WHEN
        var actual = controller.getAll();

        //THEN
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actual.getBody()).hasSameElementsAs(list);
    }
}