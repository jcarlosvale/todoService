package com.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private String username;
    private String name;

}
