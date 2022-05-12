package com.study.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categoria")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorySet")
//    private Set<Todo> todoSet;
}
