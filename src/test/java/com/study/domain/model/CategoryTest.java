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
        var dto = new Category(id, description);

        //THEN
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescription()).isEqualTo(description);
    }
}