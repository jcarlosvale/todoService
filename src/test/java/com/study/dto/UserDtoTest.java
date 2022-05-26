package com.study.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class UserDtoTest {

    @Test
    void testarConstructor(){
        //GIVEN
        String username = "example";
        String name = "some name";
        //WHEN
        var dto = new UserDto(username, name);
        //THEN
        assertThat(dto.getUsername()).isEqualTo(username);
       assertThat(dto.getName()).isEqualTo(name);
    }

    @MethodSource("invalidFields")
    void testarConstrutorInvalido(final String username, final String name){
        //GIVEN
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        //WHEN

        var dto = new UserDto(username, name);

        //THEN
        Set<ConstraintViolation<UserDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.size()).isEqualTo(1);
        assertThat(
                violations.stream()
                        .findFirst().get().getMessage()).isEqualTo("Nao pode ser blank");

    }

    private static Stream<Arguments> invalidFields() {
        return Stream.of(
                Arguments.arguments(null, null),
                Arguments.arguments("example", null),
                Arguments.arguments(null, "name"),
                Arguments.arguments("example", "name")
        );
    }


}