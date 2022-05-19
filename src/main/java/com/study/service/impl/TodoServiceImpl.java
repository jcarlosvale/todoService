package com.study.service.impl;

import com.study.domain.model.TodoEntity;
import com.study.domain.model.UserEntity;
import com.study.domain.repository.TodoRepository;
import com.study.dto.ResponseBoredDto;
import com.study.dto.TodoDto;
import com.study.dto.UserDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;
import com.study.service.TodoService;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate restTemplate;

    @Override
    public List<TodoDto> getTodoListFromUser(final String username)  {

        checkNotNull(username, "username null");

        final UserEntity userEntity = userService.findUserByUsername(username);

        return repository
                .findAllByUserEntityEquals(userEntity)
                .stream()
                .map(todoEntity -> new TodoDto(todoEntity.getId(), userEntity.getUsername(), todoEntity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTodoFromUser(final String username, final long todoId)
            throws UserNotFoundException, TodoNotFoundException {
        try {

            checkNotNull(username);
            checkArgument(todoId > 0);

            userService.findUserByUsername(username);

            final TodoEntity todoEntity = findTodoById(todoId);

            repository.delete(todoEntity);

        } catch (UserNotFoundException e) {
            log.error("Usuario nao encontrado: " + username);
            log.error(e.getMessage());
            throw e;
        } catch (TodoNotFoundException e) {
            log.error("TodoEntity nao encontrado: " + todoId);
            log.error(e.getMessage());
            throw e;
        }
    }

    private TodoEntity findTodoById(final long todoId) throws TodoNotFoundException {

        checkArgument(todoId > 0);

        return repository
                .findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("TodoEntity nao encontrado"));
    }

    @Override
    public void updateTodo(final TodoDto todoDto) throws UserNotFoundException {

        checkNotNull(todoDto);

        final UserEntity userEntity = userService.findUserByUsername(todoDto.getUsername());
        final TodoEntity todoEntity = TodoEntity.builder()
                .id(todoDto.getId())
                .description(todoDto.getDescription())
                .userEntity(userEntity)
                .build();
        repository.save(todoEntity);

    }

    @Override
    public void saveTodo(final TodoDto todoDto) throws UserNotFoundException {
        
        checkNotNull(todoDto);
        
        final UserEntity userEntity = userService.findUserByUsername(todoDto.getUsername());
        final TodoEntity todoEntity = TodoEntity.builder()
                .description(todoDto.getDescription())
                .userEntity(userEntity)
                .build();
        repository.save(todoEntity);
    }

    @Override
    public TodoDto generateRandomTodo(final UserDto userDto) {

        checkNotNull(userDto);

        final ResponseBoredDto responseBoredApi = getActivityFromBoredAPI();

        UserEntity userEntity = userService.findUserByUsername(userDto.getUsername());

        final TodoEntity entity = transformToTodoEntity(responseBoredApi, userEntity);

        repository.save(entity);

        final TodoDto dto = transformToDto(entity);

        return  dto;
    }

    private TodoDto transformToDto(final TodoEntity entity) {
        return
                TodoDto.builder()
                        .id(entity.getId())
                        .description(entity.getDescription())
                        .username(entity.getUserEntity().getUsername())
                        .build();
    }

    private TodoEntity transformToTodoEntity(final ResponseBoredDto responseBoredApi, final UserEntity userEntity) {
        return
                TodoEntity.builder()
                        .description(responseBoredApi.getDescription())
                        .userEntity(userEntity)
                        .build();
    }

    private ResponseBoredDto getActivityFromBoredAPI() {
        return restTemplate
                .getForEntity("https://www.boredapi.com/api/activity", ResponseBoredDto.class)
                .getBody();
    }

}
