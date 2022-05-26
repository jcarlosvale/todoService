package com.study.domain.model;

import com.study.dto.CategoriaDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testarConstrutor() {
        //GIVEN
        Long id = 3L;
        String description = "a happy description";

        //WHEN
        var category = new CategoriaDto(id, description);

        //THEN
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getDescription()).isEqualTo(description);
    }


}