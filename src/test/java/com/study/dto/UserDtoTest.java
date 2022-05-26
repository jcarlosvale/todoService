package com.study.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testarConstrutor() {
        //GIVEN
        String username = "username";
        String name = "some name";

        //WHEN
        var dto = new UserDto(username, name);

        //THEN
        assertThat(dto.getUsername()).isEqualTo(username);
        assertEquals(name, dto.getName());
    }
}