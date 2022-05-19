package com.study.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoDto {

    private Long id;

    //@NotBlank

    private String username;

    @NotBlank
    private String description;


    @JsonCreator
    public TodoDto(@JsonProperty final Long id,
                   @JsonProperty(value = "username", required = true) final String username,
                   @JsonProperty final String description) {
        this.id = id;
        this.username = username;
        this.description = description;
    }
}
