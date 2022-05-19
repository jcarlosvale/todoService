package com.study.service;

import com.study.dto.TodoDto;
import com.study.dto.UserDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;

import java.util.List;

public interface TodoService {

    List<TodoDto> getTodoListFromUser(String userName);

    void deleteTodoFromUser(String userName, long todoId) throws UserNotFoundException, TodoNotFoundException;

    void updateTodo(TodoDto todoDto) throws UserNotFoundException;

    void saveTodo(TodoDto todoDto) throws UserNotFoundException;

    TodoDto generateRandomTodo(UserDto userDto);
}
