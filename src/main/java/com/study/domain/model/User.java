package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class User {

    @Id
    private String username;

    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Todo> toDoList;
}
