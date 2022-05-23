package com.study.controller;

import com.study.dto.CategoriaDto;
import com.study.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    final CategoryService service;

    @GetMapping(path = "/categorias")
    public ResponseEntity<List<CategoriaDto>> retornarCategorias() {
        return ResponseEntity.ok(service.retrieveAll());
    }

    @PostMapping(path="/categorias")
    public ResponseEntity<Void> criarCategoria(@RequestBody final CategoriaDto dto) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path="/categorias/{id}")
    public ResponseEntity<Void> removerCategoria(@PathVariable(name="id") final long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path="/categorias")
    public ResponseEntity<Void> atualizarCategoria(@RequestBody final CategoriaDto dto) {
        service.update(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
