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

    @GetMapping(path="/users/{username}/todos")
    public ResponseEntity<List<TodoDto>> getTodoListFromUser(@PathVariable(name="username") String username)
            throws UserNotFoundException {

        List<TodoDto> todoDtoList =  service.getTodoListFromUser(username);

        return ResponseEntity.ok(todoDtoList);
    }

    @DeleteMapping(path="/users/{username}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodoFromUser(@PathVariable(name="username") String username,
                                                   @PathVariable(name="todoId") long todoId)
            throws UserNotFoundException, TodoNotFoundException {

        service.deleteTodoFromUser(username, todoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path="/todos")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoDto todoDto) throws UserNotFoundException {

        service.updateTodo(todoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path="/todos")
    public ResponseEntity<Void> saveTodo(@RequestBody TodoDto todoDto) throws UserNotFoundException {

        service.saveTodo(todoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
