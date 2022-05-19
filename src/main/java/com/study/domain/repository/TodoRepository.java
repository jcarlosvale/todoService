package com.study.domain.repository;

import com.study.domain.model.TodoEntity;
import com.study.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByUserEntityEquals(UserEntity user);

}
