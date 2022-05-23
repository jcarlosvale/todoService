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

class CategoriaDtoTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "some description";

    @Test
    void construct() {
        //GIVEN
        //WHEN
        final var dto = new CategoriaDto(ID, DESCRIPTION);

        //THEN
        assertThat(dto.getId())
                .isEqualTo(ID);
        assertThat(dto.getDescription())
                .isEqualTo(DESCRIPTION);
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void construct_notAllowedValues(final long id, final String description) {
        //GIVEN
        final String expectedMessage = "Nao pode ser blank";
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        //WHEN
        final var dto = new CategoriaDto(id, description);

        //THEN
        Set<ConstraintViolation<CategoriaDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(expectedMessage);


    }

    private static Stream<Arguments> invalidFields() {
        return Stream.of(
                Arguments.arguments(ID, null),
                Arguments.arguments(ID, ""),
                Arguments.arguments(ID, " ")
        );
    }

}