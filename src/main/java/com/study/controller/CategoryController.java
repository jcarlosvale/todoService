package com.study.controller;

import com.study.dto.CategoryDto;
import com.study.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CategoryController {

private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    //FIXME: implementar método listar todas as CATEGORIA
        @GetMapping(path="/category")
        public ResponseEntity<List<CategoryDto>> getCategoryList (){

            return null;
        }


    //FIXME: implementar método de REMOVER CATEGORIA
    @DeleteMapping(path="/category/{categoryId}/")
    public ResponseEntity<Void> deleteCategory (@PathVariable(name="categoryID") long id){
        return null;
    }


    //FIXME: implementar método de ATUALIZAR CATEGORIA

    @PutMapping(path = "/category")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto){

        return null;
    }
    //FIXME: implementar método de  CRIAR CATEGORIAS
        @PostMapping(path="/category")
        public ResponseEntity<Void> saveCategory(@RequestBody CategoryDto categoryDto){

            return null;
        }
}