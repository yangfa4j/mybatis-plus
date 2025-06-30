package com.test.mapstruct.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    // 静态实例，用于调用映射方法
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // 定义映射方法：User → UserDto
    UserDto userToUserDto(User user);
}