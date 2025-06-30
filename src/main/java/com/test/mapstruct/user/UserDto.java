package com.test.mapstruct.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private Integer age;
    // Getter、Setter 或 Lombok 注解（@Data 等）
}