package com.study.controller;

import com.study.dto.TodoDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;
import com.study.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public ResponseEntity<List<TodoDto>> getTodoListFromUser(@PathVariable(name="userName") String userName)
            throws UserNotFoundException {

        List<TodoDto> todoDtoList =  service.getTodoListFromUser(userName);

        return ResponseEntity.ok(todoDtoList);
    }

    @DeleteMapping(path="/users/{userName}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodoFromUser(@PathVariable(name="userName") String userName,
                                                   @PathVariable(name="todoId") long todoId)
            throws UserNotFoundException, TodoNotFoundException {

        service.deleteTodoFromUser(userName, todoId);

        return ResponseEntity.noContent().build();
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
