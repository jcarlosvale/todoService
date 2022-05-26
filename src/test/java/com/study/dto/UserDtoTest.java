package com.study.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {
    @Test
    void testarConstrutor() {
        //GIVEN
        String username = "jake@jake";
        String name = "jakeline";

        //WHEN
        var dto = new UserDto(username, name);

        //THEN
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getName()).isEqualTo(name);
    }
}