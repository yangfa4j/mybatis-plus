package com.test.annotation;

import lombok.Data;

@Data
@Table("user")
public class User {

    @Column("id")
    private int id;
    @Column("name")
    private String name;
    @Column("age")
    private int age;
    @Column("address")
    private String address;

}
