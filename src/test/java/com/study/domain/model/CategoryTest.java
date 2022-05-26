package com.study.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    void testarConstrutor() {
        //GIVEN
        Long id = 1L;
        String description = "some description";

        //WHEN
        var entity = new Category(id, description);

        //THEN
        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescription()).isEqualTo(description);
    }
}