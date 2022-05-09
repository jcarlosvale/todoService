package com.study.service;

import com.study.dto.TodoDto;

import java.util.List;

public interface TodoService {

    List<TodoDto> getTodoListFromUser(String userName);

    void deleteTodoFromUser(String userName, int todoId);

    /**
     * Localiza e atualiza o Todo.
     * @param todoDto
     */
    void updateTodo(TodoDto todoDto);

    /**
     * Verifica se não existe e então salva o Todo.
     * @param todoDto
     */
    void saveTodo(TodoDto todoDto);
}
