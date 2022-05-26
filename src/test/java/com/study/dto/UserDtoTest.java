package com.study.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoTest {
    @Test
    void testarConstrutor() {
        //GIVEN
        String name = "a name";
        String username = "a username";

        //WHEN
        var dto = new UserDto(username, name);

        //THEN
        assertThat(dto.getUsername()).isEqualTo(username);
        assertThat(dto.getName()).isEqualTo(name);
    }


}
