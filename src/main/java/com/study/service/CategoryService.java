package com.study.service;

import com.study.dto.CategoriaDto;

import java.util.List;

public interface CategoryService {

    List<CategoriaDto> retrieveAll();


    void save(CategoriaDto dto);


    void delete(long id);


    void update(CategoriaDto dto);
}
