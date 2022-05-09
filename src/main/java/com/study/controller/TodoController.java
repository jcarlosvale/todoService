package com.study.controller;

import com.study.dto.TodoDto;
import com.study.service.TodoService;
import com.study.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService service;

    @GetMapping(path="/users/{userName}/todos")
    public ResponseEntity<List<TodoDto>> getTodoListFromUser(@PathVariable(name="userName") String userName) {

        List<TodoDto> todoDtoList =  service.getTodoListFromUser(userName);
        return new ResponseEntity(todoDtoList, HttpStatus.OK);

    }

    @DeleteMapping(path="/users/{userName}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodoFromUser(@PathVariable(name="userName") String userName,
                                                   @PathVariable(name="todoId") int todoId){

        service.deleteTodoFromUser(userName, todoId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/todos")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoDto todoDto){

        service.updateTodo(todoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path="/todos")
    public ResponseEntity<Void> saveTodo(@RequestBody TodoDto todoDto){

        service.saveTodo(todoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
