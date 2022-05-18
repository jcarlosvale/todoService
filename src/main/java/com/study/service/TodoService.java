package com.study.service;

import com.study.dto.TodoDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;

import java.util.List;

public interface TodoService {

    List<TodoDto> getTodoListFromUser(String userName) throws UserNotFoundException;

    void deleteTodoFromUser(String userName, long todoId) throws UserNotFoundException, TodoNotFoundException;

    /**
     * Localiza e atualiza o Todo.
     * @param todoDto
     */
    void updateTodo(TodoDto todoDto) throws UserNotFoundException;

    /**
     * Verifica se não existe e então salva o Todo.
     * @param todoDto
     */
    void saveTodo(TodoDto todoDto) throws UserNotFoundException;
}
