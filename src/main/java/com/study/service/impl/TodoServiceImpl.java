package com.study.service.impl;

import com.study.domain.model.Todo;
import com.study.domain.model.User;
import com.study.domain.repository.TodoRepository;
import com.study.dto.TodoDto;
import com.study.exception.TodoNotFoundException;
import com.study.exception.UserNotFoundException;
import com.study.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    //FIXME: IMPLEMENTAR INTERFACE
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
        //FIXME: implementar UpdateTodo
        //PASSO 1: fazer validações: verificar se o Todo existe, senão lançar exceção.
        //PASSO 2:  usar método save() do repositório com o ToDo entity.
    }

    @Override
    public void saveTodo(TodoDto todoDto) {
        //FIXME: implementar UpdateTodo
        // PASSO 1:  usar método save() do repositório com o ToDo entity.
        //OBS: o Todo não deve possuir ID, porque é gerado por autoincremento, veja a classe Todo. Precisa validar o parâmetro?
    }
}
