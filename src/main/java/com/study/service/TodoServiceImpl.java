package com.study.service;

import com.study.domain.model.Todo;
import com.study.domain.model.User;
import com.study.domain.repository.TodoRepository;
import com.study.dto.TodoDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final UserService userService;
    private final TodoRepository repository;

    @Override
    public List<TodoDto> getTodoListFromUser(String username) throws UserNotFoundException {
        try {
            User user = userService.findUserByUsername(username);

            return repository
                    .findAllByUserEquals(user)
                    .stream()
                    .map(todo -> new TodoDto(todo.getId(), user.getUsername(), todo.getDescription()))
                    .collect(Collectors.toList());

        } catch (UserNotFoundException e) {
            log.error("Usuario nao encontrado: " + username);
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteTodoFromUser(String username, long todoId)
            throws UserNotFoundException, TodoNotFoundException {
        try {

            userService.findUserByUsername(username);

            Todo todo = findTodoById(todoId);

            repository.delete(todo);

        } catch (UserNotFoundException e) {
            log.error("Usuario nao encontrado: " + username);
            log.error(e.getMessage());
            throw e;
        } catch (TodoNotFoundException e) {
            log.error("Todo nao encontrado: " + todoId);
            log.error(e.getMessage());
            throw e;
        }
    }

    private Todo findTodoById(long todoId) throws TodoNotFoundException {
        return repository
                .findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo nao encontrado"));
    }

    @Override
    public void updateTodo(TodoDto todoDto) {

    }

    @Override
    public void saveTodo(TodoDto todoDto) {

    }
}
