package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class UserEntity {

    @Id
    private String username;

    @NotBlank
    @Size(min = 3)
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
//    private List<TodoEntity> toDoList;
}
