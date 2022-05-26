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
import static org.junit.jupiter.api.Assertions.*;

class CategoriaDtoTest {

    @Test
    void testarConstrutor() {
        //GIVEN
        Long id = 1L;
        String description = "some description";

        //WHEN
        var dto = new CategoriaDto(id, description);

        //THEN
        assertThat(dto.getId()).isEqualTo(id);
        assertEquals(description, dto.getDescription());
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void testValidationConstructor(final long id, final String description) {
        //GIVEN
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        //WHEN
        var dto = new CategoriaDto(id, description);

        //THEN
        Set<ConstraintViolation<CategoriaDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.size()).isEqualTo(1);
        assertThat(
                violations.stream()
                        .findFirst().get().getMessage()).isEqualTo("Can't be blank");
    }

    private static Stream<Arguments> invalidFields() {
        return Stream.of(
                Arguments.arguments(1L, null),
                Arguments.arguments(1L, ""),
                Arguments.arguments(1L, " ")
        );
    }
}