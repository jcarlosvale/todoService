package com.study.service;

import com.study.domain.model.User;
import com.study.domain.repository.UserRepository;
import com.study.dto.TodoDto;
import com.study.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final UserService userService;

    @Override
    public List<TodoDto> getTodoListFromUser(String username) throws UserNotFoundException {

        //localizar usuario

        try {
            User user = userService.findUserByUsername(username);
            return List.of();
        } catch (UserNotFoundException e) {
            log.error("Usuario nao encontrado: " + username);
            log.error(e.getMessage());
            throw e;
        }



        //--> erro (404)
        //select retornar os todos daquele usuario
        //converter DTO
        //retornar
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
