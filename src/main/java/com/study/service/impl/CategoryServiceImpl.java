package com.study.service.impl;

import com.study.domain.model.Category;
import com.study.domain.repository.CategoryRepository;
import com.study.dto.CategoriaDto;
import com.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    final CategoryRepository repository;

    @Override
    public List<CategoriaDto> retrieveAll() {
        return repository
                .findAll()
                .stream()
                .map(entity ->
                        CategoriaDto.builder()
                        .id(entity.getId())
                        .description(entity.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void save(final CategoriaDto dto) {
        final Category entity =
                Category.builder()
                .description(dto.getDescription())
                .build();

        repository.save(entity);
    }

    @Override
    public void delete(long id) {
        Category entity = repository.getById(id);
        repository.delete(entity);
    }

    @Override
    public void update(CategoriaDto dto) {
        final Category entity =
                Category.builder()
                        .id(dto.getId())
                        .description(dto.getDescription())
                        .build();

        repository.save(entity);
    }
}
