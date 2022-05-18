package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //cria objeto
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)  //FetchType.EAGER
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categorySet;
}
