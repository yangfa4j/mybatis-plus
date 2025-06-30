package com.test.mapstruct.user;



public class App {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setAge(20);

        // 执行映射
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
        System.out.println(userDto);
    }
}