package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "todo")
public class TodoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="Descricao nao deve ficar vazia")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)  //FetchType.EAGER
    private UserEntity userEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categorySet;
}
