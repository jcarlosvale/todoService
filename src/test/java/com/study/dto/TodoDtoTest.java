package com.study.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TodoDtoTest {

    @Test
    void testarConstructor (){
       //given
        Long id = 1L;
        String username = "Re";
        String description = "description";

        //when
        var dto = new TodoDto(id, username, description);

        //then
        assertThat(id).isEqualTo(dto.getId());
        assertThat(username).isEqualTo(dto.getUsername());
        assertThat(description).isEqualTo(dto.getDescription());
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void testValidationConstructor (final Long id, final String username, final String description){
        //GIVEN
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        //WHEN
        var dto = new TodoDto(id, username, description);

        //THEN
        Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.size()).isEqualTo(1);
        assertThat(
                violations.stream()
                        .findFirst().get().getMessage()).isEqualTo("não deve estar em branco");
    }

    private static Stream<Arguments> invalidFields(){
        return Stream.of(
                Arguments.arguments(1L, "username", null),
                Arguments.arguments(1L, "username", ""),
                Arguments.arguments(1L, "username", " ")
        );
    }

}