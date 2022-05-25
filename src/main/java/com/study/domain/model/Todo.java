package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Descricao nao deve ser vazia")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)  //FetchType.EAGER
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categorySet;
}
