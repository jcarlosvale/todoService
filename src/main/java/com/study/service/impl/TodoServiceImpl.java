package com.study.service.impl;

import com.study.domain.model.Todo;
import com.study.domain.model.User;
import com.study.domain.repository.TodoRepository;
import com.study.dto.TodoDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;
import com.study.service.TodoService;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final UserService userService;
    private final TodoRepository repository;

    @Override
    public List<TodoDto> getTodoListFromUser(final String username) throws UserNotFoundException {
        try {
            checkNotNull(username, "username null");

            final User user = userService.findUserByUsername(username);

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
    public void deleteTodoFromUser(final String username, final long todoId)
            throws UserNotFoundException, TodoNotFoundException {
        try {

            checkNotNull(username);
            checkArgument(todoId > 0);

            userService.findUserByUsername(username);

            final Todo todo = findTodoById(todoId);

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

    private Todo findTodoById(final long todoId) throws TodoNotFoundException {

        checkArgument(todoId > 0);

        return repository
                .findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo nao encontrado"));
    }

    @Override
    public void updateTodo(final TodoDto todoDto) throws UserNotFoundException {

        checkNotNull(todoDto);

        final User user = userService.findUserByUsername(todoDto.getUsername());
        final Todo todo = Todo.builder()
                .id(todoDto.getId())
                .description(todoDto.getDescription())
                .user(user)
                .build();
        repository.save(todo);

    }

    @Override
    public void saveTodo(final TodoDto todoDto) throws UserNotFoundException {
        final User user = userService.findUserByUsername(todoDto.getUsername());
        final Todo todo = Todo.builder()
                .description(todoDto.getDescription())
                .user(user)
                .build();
        repository.save(todo);
    }
}
