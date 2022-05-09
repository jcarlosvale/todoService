package com.study.service;

import com.study.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    @Override
    public List<TodoDto> getTodoListFromUser(String userName) {
        return null;
    }

    @Override
    public void deleteTodoFromUser(String userName, int todoId) {

    }

    @Override
    public void updateTodo(TodoDto todoDto) {

    }

    @Override
    public void saveTodo(TodoDto todoDto) {

    }
}
