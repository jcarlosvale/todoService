package com.study.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TodoEntityTest {


    @ParameterizedTest
    @MethodSource("validFields")
    void testarConstrutor(final long id, final String description, final UserEntity userEntity, final Set<Category> categorySet) {
        //GIVEN
        //WHEN
        var entity = new TodoEntity(id, description, userEntity, categorySet);

        //THEN
        assertThat(entity.getId())
                .isEqualTo(id);
        assertThat(entity.getDescription())
                .isEqualTo(description);
        assertThat(entity.getUserEntity())
                .isEqualTo(userEntity);
        assertThat(entity.getCategorySet())
                .isEqualTo(categorySet);
    }

    private static Stream<Arguments> validFields() {
        final UserEntity userEntity = Mockito.mock(UserEntity.class);
        final Set<Category> categorySet = Mockito.mock(Set.class);

        return Stream.of(
                arguments(1L, "some description", null, null),
                arguments(1L, "some description", userEntity, categorySet),
                arguments(1L, "some description", userEntity, null),
                arguments(1L, "some description", null, categorySet));
    }

    @ParameterizedTest
    @MethodSource("invalidFields")
    void construct_notAllowedValues(final long id, final String description, final UserEntity userEntity, final Set<Category> categorySet, final String errorMessage) {
        //GIVEN
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        //WHEN
        final var entity = new TodoEntity(id, description, userEntity, categorySet);

        //THEN
        Set<ConstraintViolation<TodoEntity>> violations = validator.validate(entity);
        assertThat(violations).isNotEmpty();
        assertThat(violations.size()).isEqualTo(1);
        assertThat(violations.stream().findFirst().get().getMessage()).isEqualTo(errorMessage);
    }

    private static Stream<Arguments> invalidFields() {
        return Stream.of(
                arguments(1L, null, null, null, "Descricao nao deve ficar vazia"),
                arguments(1L, "", null, null, "Descricao nao deve ficar vazia")
        );
    }

}